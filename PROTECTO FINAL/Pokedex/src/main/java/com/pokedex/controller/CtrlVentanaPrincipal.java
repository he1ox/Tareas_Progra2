/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pokedex.controller;

import com.pokedex.dao.PokemonDao;
import com.pokedex.dao.UsuarioDao;
import com.pokedex.model.Pokemon;
import com.pokedex.model.ReportesPdf;
import com.pokedex.model.TablaFavoritos;
import com.pokedex.model.TablaPokemon;
import com.pokedex.model.Usuario;
import com.pokedex.model.querySql;
import com.pokedex.view.VentanaPrincipal;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author georg
 */
public class CtrlVentanaPrincipal implements ActionListener {

    public static VentanaPrincipal view;
    private int CONTADOR = 0;
    private PokemonDao pokemonDao;
    private Pokemon pokemon;
    private TablaPokemon tabla;
    private TablaFavoritos tablaFavoritos;
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private ReportesPdf reporte;
    
    public CtrlVentanaPrincipal(VentanaPrincipal vista, PokemonDao pokemonDao , Pokemon pokemon, UsuarioDao usuarioDao, Usuario usuario) {
        CtrlVentanaPrincipal.view = vista;
        
        reporte = new ReportesPdf();
        
        this.usuarioDao = usuarioDao;
        this.usuario = usuario;
        System.out.println(usuario);
        
        this.pokemonDao = pokemonDao;
        this.pokemon = pokemon;
        
        tabla = new TablaPokemon(view.tbPokemones);
        tabla.actualizar();
        
        tablaFavoritos = new TablaFavoritos(view.tbFavoritos, usuario);
        tablaFavoritos.actualizar();
        
        
        view.setTitle("POKEDEX v1.0 - Bienvenido " + usuario.getNombre() + " !");
        
        view.btnPdf.addActionListener(this);
        view.filtroOpciones.addActionListener(this);
        view.btnAgregarFavoritos.addActionListener(this);
        view.btnRecargar.addActionListener(this);
        view.btnBuscar.addActionListener(this);
        view.btnDerecha.addActionListener(this);
        view.btnIzquierda.addActionListener(this);
        view.tbPokemones.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting() && view.tbPokemones.getSelectedRow() != -1) {
                int id = (int) view.tbPokemones.getValueAt(view.tbPokemones.getSelectedRow(), 0);
                
                drawPokemon(id-1);
                this.pokemon = this.pokemonDao.buscarPorId(id);
                actualizarInfo();
                
            }
        });

        try {
            view.imagen1 = ImageIO.read(new File("C:\\Users\\georg\\Documents\\datos\\imagenes\\black-white.png"));

            view.buffer1 = (BufferedImage) view.JPanelPokemon.createImage(
                    view.JPanelPokemon.getWidth(),
                    view.JPanelPokemon.getHeight()
            );

            Graphics2D g2 = view.buffer1.createGraphics();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Falló al importar la imagen.");
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == view.btnDerecha) {
            drawPokemon(CONTADOR);
            
            
            this.pokemon = pokemonDao.buscarPorId(CONTADOR+1);
            
            
            actualizarInfo();
            
            CONTADOR++;
        }
        
        else if (e.getSource() == view.btnIzquierda) {
            CONTADOR--;
            
            if (CONTADOR<=0) {
                CONTADOR = 0;
            }
            
            drawPokemon(CONTADOR);
            
            this.pokemon = pokemonDao.buscarPorId(CONTADOR+1);
            actualizarInfo();
            
            
        }
        
        else if (e.getSource() == view.tbPokemones) {
            int id = view.tbPokemones.getSelectedRow();
            
            this.pokemon = this.pokemonDao.buscarPorId(id);
            
            view.txtNombrePokemon.setText(this.pokemon.getName());
        }

        else if (e.getSource() == view.btnBuscar) {
            String nombre = view.txtboxBuscarPokemon.getText();
            
            tabla.buscarPor(nombre);
        }
        
        else if (e.getSource() == view.btnRecargar) {
            tabla.actualizar();
        }
        
        else if (e.getSource() == view.btnAgregarFavoritos) {
            usuarioDao.agregarPokemon(usuario, pokemon);
            tablaFavoritos.actualizar();
        }
        else if (e.getSource() == view.filtroOpciones) {
            String opcion = view.filtroOpciones.getSelectedItem().toString();
            
            tabla.aplicarFiltro(opcion);
        } else if (e.getSource() == view.btnPdf) {
            reporte.generar();
        }
    }
    
    public void actualizarInfo(){
        view.txtNombrePokemon.setText(this.pokemon.getName());
        view.txtAltura.setText(String.valueOf(this.pokemon.getHeight()) + " cm.");
        view.txtCategoria.setText(this.pokemon.getSpecies());
        view.txtHabitat.setText(this.pokemon.getHabitat());
        view.txtPeso.setText(String.valueOf(this.pokemon.getWeight()) + " lb.");
    }
    
    public void drawPokemon(int posicion){
        int fila = posicion / 31;
        int columna = posicion & 31;
        
        Graphics2D g2 = (Graphics2D) view.buffer1.getGraphics();
        g2.setColor(Color.black);
        
        g2.fillRect(0,0,view.JPanelPokemon.getWidth(), view.JPanelPokemon.getHeight());
        
        g2.drawImage(view.imagen1, 0,0, view.JPanelPokemon.getWidth(),view.JPanelPokemon.getHeight(),
                columna*96,
                fila*96,
                columna*96+96,
                fila*96+96,
                null);
        view.repaint();
    }
}

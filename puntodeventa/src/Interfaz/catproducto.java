/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import puntoventaBean.FamiliaBean;
import puntoventaBean.ProductoBean;
import puntoventaDao.FamiliaDao;
import puntoventaDao.ProductoDao;
import puntoventaDao.ProveedorDao;
import puntoventaDao.UmedidaDao;

/**
 *
 * @author Joaquin
 */
public class catproducto extends javax.swing.JFrame {

    String Link = null;
    ResultSet rs = null;
    DefaultTableModel mtabla;
    ProductoBean ProdBean = null;
    ProductoDao ProdDao = new ProductoDao();
    FamiliaBean fambean;
    File fichero;
    DefaultComboBoxModel value;

    /**
     * Creates new form catproducto
     * cbnombre.setSelectedIndex(setSelectedValue(cbnombre,"joge"));
     */
    public catproducto() {
        initComponents();
        llenar();
        cargacomboFamilia();
        cargarcomboproveedor();
        cargarunidadmedida();
        String valor = "6";

    }

    void llenar() {
        String[] titulos = {"CodBarra", "Descripcion", "Familia", "P_Compra", "P_Venta", "Existencia", "Uni_Medida"};
        mtabla = new DefaultTableModel(null, titulos);
        rs = ProdDao.catproducto();
        String[] fila = new String[7];
        try {
            while (rs.next()) {
                fila[0] = rs.getString("CodBarra");
                fila[1] = rs.getString("Descripcion");
                fila[2] = rs.getString("Familia");
                fila[3] = rs.getString("P_Compra");
                fila[4] = rs.getString("P_Venta");
                fila[5] = rs.getString("Existencia");
                fila[6] = rs.getString("Uni_Medida");
                mtabla.addRow(fila);
            }
            tablaproduc.setModel(mtabla);
        } catch (Exception e) {
        }
        ponertamaño();
    }

    void ponertamaño() {
        TableColumnModel columnModel = tablaproduc.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(70);
        columnModel.getColumn(1).setPreferredWidth(300);
    }

    /*
       void llenarcombo(){
        value = new DefaultComboBoxModel();
        value.addElement(new persona("Seleccione", "1"));
        value.addElement(new persona("esteban", "2"));
        cbnombre.setModel(value);
    }
     */
    private void cargacomboFamilia() {
        value = new DefaultComboBoxModel();
        FamiliaDao FamDao = new FamiliaDao();
        rs = FamDao.catFamiliaD();
        try {
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("Clave"));
                value.addElement(new FamiliaBean(id, rs.getString("Descripcion")));
            }
            cbFamilia.setModel(value);
        } catch (Exception e) {
        }
        // cbFamilia.getSelectedIndex();
    }

    private void cargarcomboproveedor() {
        ProveedorDao ProDao = new ProveedorDao();
        rs = ProDao.catproveedor();
        try {
            while (rs.next()) {
                cbproveedor.addItem(rs.getString("Nombre"));
            }
        } catch (Exception e) {
        }
        //cbproveedor.setSelectedIndex(1);
    }

    final void cargarunidadmedida() {
        UmedidaDao UmeDao = new UmedidaDao();
        rs = UmeDao.catUmedida();
        try {
            while (rs.next()) {
                cbunidadmed.addItem(rs.getString("Descripcion"));
            }
        } catch (Exception e) {
        }

    }

    public void asignarEntradas() {
        txtclave.setText(txtclave.getText().trim());
        txtdescripcion.setText(txtdescripcion.getText().trim());
        ProdBean = new ProductoBean();
        ProdBean.setIdproducto(txtclave.getText());
        ProdBean.setDescripcion(txtdescripcion.getText());
        // ProdBean.setIdfamilia(cbFamilia.getSelectedIndex());
        ProdBean.setPcompra(Float.valueOf(txtpcompra.getText()));
        ProdBean.setPventa(Float.valueOf(txtpventa.getText()));
        ProdBean.setExistencia(Float.valueOf(txtexistencia.toString()));
        ProdBean.setCantidadminima(Float.valueOf(txtcantidadminima.getText()));
//        ProdBean.setUmedida(cbunidadmed.getSelectedItem());
        //ProdBean.setImagen(txtimagen);
//        ProdBean.setIdproveedor(cbproveedor.getSelectedIndex());
        ProdBean.setObservaciones(txtobservacion.getText());
    }

    void cargarfotonueva() {
        int resultado;
        filechoose ventana = new filechoose();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG", "jpg", "png");
        filechoose.JfileChose.setFileFilter(filtro);
        resultado = filechoose.JfileChose.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == resultado) {
            fichero = filechoose.JfileChose.getSelectedFile();
            Link = fichero.toString();
            try {
                ImageIcon icon = new ImageIcon(fichero.toString());
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lbimagen.getWidth(), lbimagen.getHeight(), 2));
                lbimagen.setText(null);
                lbimagen.setIcon(icono);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error abriendo la imagen " + ex);
            }
        }
    }

    void fotoexistente(String fichero) {
        Link = fichero;
        try {
            ImageIcon icon = new ImageIcon(fichero);
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lbimagen.getWidth(), lbimagen.getHeight(), 2));
            lbimagen.setText(null);
            lbimagen.setIcon(icono);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error abriendo la imagen " + ex);
        }
    }

    void limpiar() {
        txtclave.setText("");
        txtdescripcion.setText("");
        cbFamilia.setSelectedIndex(0);
        txtpcompra.setText("");
        txtpventa.setText("");
        txtexistencia.setText("");
        txtcantidadminima.setText("");
        cbunidadmed.setSelectedIndex(0);
        fotoexistente(null);
        cbproveedor.setSelectedIndex(0);
        txtobservacion.setText("");
    }

    public static int setSelectedValue(JComboBox cbbo, String nombre) {
        for (int i = 0; i < cbbo.getItemCount(); i++) {
            Object item = cbbo.getItemAt(i);
            if (item.toString().compareTo(nombre) == 0) {
                return i;
            }
        }
        return 0;
    }

    void obtenerIDFam() {
        FamiliaBean fam = (FamiliaBean) cbFamilia.getSelectedItem();
        int id = fam.getID();
        System.out.println(id);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaproduc = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtclave = new javax.swing.JTextField();
        txtdescripcion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtpventa = new javax.swing.JTextField();
        txtpcompra = new javax.swing.JTextField();
        txtexistencia = new javax.swing.JTextField();
        txtcantidadminima = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtobservacion = new javax.swing.JTextField();
        cbFamilia = new javax.swing.JComboBox<>();
        cbproveedor = new javax.swing.JComboBox<>();
        cbunidadmed = new javax.swing.JComboBox<>();
        lbimagen = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("C A T A L O G O     P R O D U C T O");

        tablaproduc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaproduc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaproducMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaproduc);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS PRODUCTO", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Californian FB", 1, 16))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(51, 51, 255));
        jPanel4.setName("Datos Cliente"); // NOI18N

        txtclave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclaveActionPerformed(evt);
            }
        });
        txtclave.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtclaveFocusLost(evt);
            }
        });

        jLabel1.setText("Clave");

        jLabel2.setText("Descripcion");

        jLabel3.setText("Familia");

        jLabel4.setText("P_Compra");

        jLabel5.setText("P_Venta");

        jLabel6.setText("Existencia");

        jLabel8.setText("Proveedor");

        jLabel9.setText("Cantidad Minima");

        jLabel10.setText("Unidad Medida");

        jLabel12.setText("Adicional");

        txtobservacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtobservacionActionPerformed(evt);
            }
        });

        cbFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFamiliaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtobservacion))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(cbunidadmed, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtpcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtpventa, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtexistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtcantidadminima, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtdescripcion)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpcompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtexistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcantidadminima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(cbunidadmed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtobservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbimagen.setToolTipText("Doble click para eliminar");
        lbimagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbimagenMouseClicked(evt);
            }
        });

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnmodificar.setText("Modificar");

        btneliminar.setText("Eliminar");

        jButton4.setText("jButton1");

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jButton5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lbimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(71, 71, 71))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtclaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclaveActionPerformed

    private void txtobservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtobservacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtobservacionActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        cargarfotonueva();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void lbimagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbimagenMouseClicked
        String link = null;
        int resp = JOptionPane.showConfirmDialog(null, "¿Eliminar foto?", "Alerta!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (resp != 1) {
            fotoexistente(link);
        }
    }//GEN-LAST:event_lbimagenMouseClicked

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        if (btnnuevo.getText().equals("Nuevo")) {
            btneliminar.setEnabled(false);
            btnmodificar.setEnabled(false);
            btnnuevo.setText("Guardar");
            limpiar();
            //  maxid();
        } else {
            if (txtdescripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campos descripcion vacio, no se guardo en la base de datos");
            } else {
                // insertarfamilia();
            }
            limpiar();
            btneliminar.setEnabled(false);
            btnmodificar.setEnabled(false);
            btnnuevo.setText("Nuevo");
        }
        llenar();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtclaveFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtclaveFocusLost
        txtclave.setText(txtclave.getText().trim());
        try {
            boolean Bol = ProdDao.buscarid(txtclave.getText());
            if (Bol == false) {
                JOptionPane.showMessageDialog(null, "Clave de producto Existente, seleccione otro");
                txtclave.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(catproducto.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_txtclaveFocusLost

    private void cbFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFamiliaActionPerformed

    }//GEN-LAST:event_cbFamiliaActionPerformed

    private void tablaproducMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaproducMouseClicked
        if (btnnuevo.getText().equals("Nuevo")) {
            int fila;
            fila = tablaproduc.rowAtPoint(evt.getPoint());
            if (fila > -1) {
                txtclave.setText(String.valueOf(tablaproduc.getValueAt(fila, 0)));
                rs = ProdDao.buscarproducto(txtclave.getText());
                try {
                    while (rs.next()) {
                        txtdescripcion.setText(rs.getString("descripcion"));
                        cbFamilia.setSelectedIndex(setSelectedValue(cbFamilia, rs.getString("familia")));
                        txtpcompra.setText(rs.getString("pcompra"));
                        txtpventa.setText(rs.getString("pventa"));
                        txtexistencia.setText(rs.getString("existencia"));
                        txtcantidadminima.setText(rs.getString("cantidadminima"));
                         cbunidadmed.setSelectedIndex(setSelectedValue(cbunidadmed, rs.getString("umedida")));
                         fotoexistente(rs.getString("imagen"));
                         cbproveedor.setSelectedIndex(setSelectedValue(cbproveedor, rs.getString("proveedor")));
                         txtobservacion.setText(rs.getString("observaciones"));
                    }
                } catch (Exception e) {
                }

                btneliminar.setEnabled(true);
                btnmodificar.setEnabled(true);
            }
        }

    }//GEN-LAST:event_tablaproducMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(catproducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(catproducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(catproducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(catproducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new catproducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JComboBox<String> cbFamilia;
    private javax.swing.JComboBox<String> cbproveedor;
    private javax.swing.JComboBox<String> cbunidadmed;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbimagen;
    private javax.swing.JTable tablaproduc;
    private javax.swing.JTextField txtcantidadminima;
    private javax.swing.JTextField txtclave;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txtexistencia;
    private javax.swing.JTextField txtobservacion;
    private javax.swing.JTextField txtpcompra;
    private javax.swing.JTextField txtpventa;
    // End of variables declaration//GEN-END:variables
}

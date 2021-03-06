/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Fabian Navarro
 */
public class VentanaFuncionario extends javax.swing.JFrame {

    /**
     * Creates new form VentanaEstudiante
     */
    public VentanaFuncionario() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    btRegistroEstudiante = new javax.swing.JButton();
    btConsultaEstudiante = new javax.swing.JButton();
    btAgregarSala = new javax.swing.JButton();
    btModificarSala = new javax.swing.JButton();
    btConsultarSala = new javax.swing.JButton();
    btAnalisis = new javax.swing.JButton();
    btConsultaReserva = new javax.swing.JButton();
    btCancelarReserva = new javax.swing.JButton();
    btSalir = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    btRegistroEstudiante.setText("Registrar Estudiante");

    btConsultaEstudiante.setText("Consultar Estudiante");

    btAgregarSala.setText("Agregar Sala");

    btModificarSala.setText("Modificar Sala");

    btConsultarSala.setText("Consultar Sala");

    btAnalisis.setText("Análisis de datos");

    btConsultaReserva.setText("Consultar Reserva");

    btCancelarReserva.setText("Cancelar Reserva");

    btSalir.setText("Salir");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(43, 43, 43)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(btSalir)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(btRegistroEstudiante)
                .addGap(18, 18, 18)
                .addComponent(btConsultaEstudiante)
                .addGap(18, 18, 18)
                .addComponent(btAgregarSala)
                .addGap(18, 18, 18))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btConsultarSala)
                .addGap(30, 30, 30)
                .addComponent(btAnalisis)
                .addGap(18, 18, 18)
                .addComponent(btConsultaReserva)
                .addGap(30, 30, 30)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(btCancelarReserva)
              .addComponent(btModificarSala))))
        .addContainerGap(30, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(63, 63, 63)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btRegistroEstudiante)
          .addComponent(btConsultaEstudiante)
          .addComponent(btAgregarSala)
          .addComponent(btModificarSala))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btAnalisis)
          .addComponent(btConsultarSala)
          .addComponent(btConsultaReserva)
          .addComponent(btCancelarReserva))
        .addGap(76, 76, 76)
        .addComponent(btSalir)
        .addGap(42, 42, 42))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VentanaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaFuncionario().setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  public javax.swing.JButton btAgregarSala;
  public javax.swing.JButton btAnalisis;
  public javax.swing.JButton btCancelarReserva;
  public javax.swing.JButton btConsultaEstudiante;
  public javax.swing.JButton btConsultaReserva;
  public javax.swing.JButton btConsultarSala;
  public javax.swing.JButton btModificarSala;
  public javax.swing.JButton btRegistroEstudiante;
  public javax.swing.JButton btSalir;
  // End of variables declaration//GEN-END:variables
  public void cerrarVentana(){
    this.dispose();
  }

}

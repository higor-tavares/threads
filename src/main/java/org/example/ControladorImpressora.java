package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ControladorImpressora extends JFrame {
  private JButton btnPausa = null;
  private JScrollPane scrlTexto = new JScrollPane();
  private JTextArea txtArea = new JTextArea();
  private Impressora impressora;

  public ControladorImpressora() {
    super("Exemplo prático Wait e Notify");

    setLayout(new BorderLayout());
    add(getBtnPausa(), BorderLayout.NORTH);
    txtArea.setEditable(false);
    scrlTexto.add(txtArea);
    scrlTexto.setViewportView(txtArea);
    add(scrlTexto, BorderLayout.CENTER);
    setSize(640,480);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    impressora = new Impressora(txtArea);
  }

  private JButton getBtnPausa() {
    if (btnPausa == null) {
      btnPausa = new JButton("Pausa");
      btnPausa.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          if (btnPausa.getText().equals("Pausa"))
          {
            btnPausa.setText("Continua");
            impressora.setPausado(true);
            return;
          }

          btnPausa.setText("Pausa");
          impressora.setPausado(false);
        }
      });
    }
    return btnPausa;
  }

  public static void main(String args[]) {
    new ControladorImpressora().setVisible(true);
  }
}


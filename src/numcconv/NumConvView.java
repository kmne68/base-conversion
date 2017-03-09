package numcconv;

import business.Bin2Dec;
import business.Conversion;
import business.Dec2Bin;
import business.Dec2Hex;
import business.Hex2Dec;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Author:
 */
public class NumConvView extends FrameView {

    public NumConvView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = NumConvApp.getApplication().getMainFrame();
            aboutBox = new NumConvAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        NumConvApp.getApplication().show(aboutBox);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jradD2B = new javax.swing.JRadioButton();
        jradB2D = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtSteps = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        lbl_value = new javax.swing.JLabel();
        jtxtValue = new javax.swing.JTextField();
        jbtnConvert = new javax.swing.JButton();
        lbl_result = new javax.swing.JLabel();
        jtxtResult = new javax.swing.JTextField();
        jbtnClear = new javax.swing.JButton();
        rdo_decToHex = new javax.swing.JRadioButton();
        jradHex2Dec = new javax.swing.JRadioButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        buttonGroup1 = new javax.swing.ButtonGroup();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(numcconv.NumConvApp.class).getContext().getResourceMap(NumConvView.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        buttonGroup1.add(jradD2B);
        jradD2B.setText(resourceMap.getString("jradD2B.text")); // NOI18N
        jradD2B.setName("jradD2B"); // NOI18N
        jradD2B.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jradD2BItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jradB2D);
        jradB2D.setText(resourceMap.getString("jradB2D.text")); // NOI18N
        jradB2D.setName("jradB2D"); // NOI18N
        jradB2D.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jradB2DItemStateChanged(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jtxtSteps.setColumns(20);
        jtxtSteps.setRows(5);
        jtxtSteps.setName("jtxtSteps"); // NOI18N
        jScrollPane1.setViewportView(jtxtSteps);

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        lbl_value.setFont(resourceMap.getFont("lbl_value.font")); // NOI18N
        lbl_value.setText(resourceMap.getString("lbl_value.text")); // NOI18N
        lbl_value.setName("lbl_value"); // NOI18N

        jtxtValue.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtValue.setText(resourceMap.getString("jtxtValue.text")); // NOI18N
        jtxtValue.setName("jtxtValue"); // NOI18N

        jbtnConvert.setText(resourceMap.getString("jbtnConvert.text")); // NOI18N
        jbtnConvert.setName("jbtnConvert"); // NOI18N
        jbtnConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnConvertActionPerformed(evt);
            }
        });

        lbl_result.setFont(resourceMap.getFont("lbl_result.font")); // NOI18N
        lbl_result.setText(resourceMap.getString("lbl_result.text")); // NOI18N
        lbl_result.setName("lbl_result"); // NOI18N

        jtxtResult.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtResult.setName("jtxtResult"); // NOI18N

        jbtnClear.setText(resourceMap.getString("jbtnClear.text")); // NOI18N
        jbtnClear.setName("jbtnClear"); // NOI18N
        jbtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClearActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdo_decToHex);
        rdo_decToHex.setText(resourceMap.getString("rdo_decToHex.text")); // NOI18N
        rdo_decToHex.setName("rdo_decToHex"); // NOI18N
        rdo_decToHex.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdo_decToHexItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jradHex2Dec);
        jradHex2Dec.setText(resourceMap.getString("jradHex2Dec.text")); // NOI18N
        jradHex2Dec.setName("jradHex2Dec"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(49, 49, 49)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jradB2D)
                                            .addComponent(jradHex2Dec))
                                        .addGap(40, 40, 40)
                                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jradD2B)
                                            .addComponent(rdo_decToHex))
                                        .addGap(0, 10, Short.MAX_VALUE))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(lbl_value)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtValue, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbtnConvert))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(lbl_result)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtResult, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnClear)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jradB2D)
                    .addComponent(jradD2B))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_decToHex)
                    .addComponent(jradHex2Dec))
                .addGap(27, 27, 27)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_value)
                    .addComponent(jtxtValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnConvert))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnClear)
                    .addComponent(lbl_result))
                .addGap(106, 106, 106))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(numcconv.NumConvApp.class).getContext().getActionMap(NumConvView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnConvertActionPerformed
        
        statusMessageLabel.setText("");
        Conversion c;

        if (jradB2D.isSelected()) {
            c = new Bin2Dec(jtxtValue.getText());
        } else if (jradD2B.isSelected()) {
            c = new Dec2Bin(jtxtValue.getText());
        } else if (rdo_decToHex.isSelected()) {
            c = new Dec2Hex(jtxtValue.getText());
        } else if (jradHex2Dec.isSelected()) {
            c = new Hex2Dec(jtxtValue.getText());
        } 
        
        else {
            statusMessageLabel.setText("Unknown operation.");
            return;
        }
        if (c.getErrorMessage().isEmpty()) {
            ArrayList<String> steps = c.getProcessLog();
            for (String step : steps) {
                jtxtSteps.append(step + "\n");
            }
            jtxtResult.setText(c.getResult());
        } else {
            statusMessageLabel.setText(c.getErrorMessage());
        }

        /*     else if (jradD2B.isSelected()) {
            Dec2Bin d2b = new Dec2Bin(jtxtValue.getText());
            if (d2b.getErrorMessage().isEmpty()) {
                ArrayList<String> steps = d2b.getProcessLog();
                for (String step : steps) {
                    jtxtSteps.append(step + "\n");
                }
                jtxtResult.setText(d2b.getResult());
            } else {
                statusMessageLabel.setText(d2b.getErrorMessage());
            }
        } else {
            statusMessageLabel.setText("Unknown operation.");
        } */

    }//GEN-LAST:event_jbtnConvertActionPerformed

    private void jbtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClearActionPerformed
        statusMessageLabel.setText("");
        jtxtValue.setText("");
        jtxtSteps.setText("");
        jtxtResult.setText("");
        jtxtValue.requestFocusInWindow();
    }//GEN-LAST:event_jbtnClearActionPerformed

    private void jradB2DItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jradB2DItemStateChanged
        if (jradB2D.isSelected()) {
            lbl_value.setText(Bin2Dec.VALUEDESCRIPTION);
            lbl_result.setText(Bin2Dec.RESULTDESCRIPTION);
        }

    }//GEN-LAST:event_jradB2DItemStateChanged

    private void jradD2BItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jradD2BItemStateChanged
        if (jradD2B.isSelected()) {
            lbl_value.setText(Dec2Bin.VALUEDESCRIPTION);
            lbl_result.setText(Dec2Bin.RESULTDESCRIPTION);
        }
    }//GEN-LAST:event_jradD2BItemStateChanged

    private void rdo_decToHexItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdo_decToHexItemStateChanged
        if (rdo_decToHex.isSelected()) {
            lbl_value.setText(Dec2Hex.VALUEDESCRIPTION);
            lbl_result.setText(Dec2Hex.RESULTDESCRIPTION);
        }
    }//GEN-LAST:event_rdo_decToHexItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnConvert;
    private javax.swing.JRadioButton jradB2D;
    private javax.swing.JRadioButton jradD2B;
    private javax.swing.JRadioButton jradHex2Dec;
    private javax.swing.JTextField jtxtResult;
    private javax.swing.JTextArea jtxtSteps;
    private javax.swing.JTextField jtxtValue;
    private javax.swing.JLabel lbl_result;
    private javax.swing.JLabel lbl_value;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JRadioButton rdo_decToHex;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}

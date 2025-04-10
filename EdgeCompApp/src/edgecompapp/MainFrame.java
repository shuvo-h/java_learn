/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edgecompapp;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.Color;


/**
 *
 * @author Biddrup Kumar Mallic
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

            /// Start threads for each sensor
        // new Thread(new SensorTask(sliderTemp, labelTempAlarm, labelTempActuator, 15, 25, 20, "Temperature")).start();
        // new Thread(new SensorTask(sliderHumidity, labelHumidityAlarm, labelHumidityActuator, 60, 80, 70, "Humidity")).start();
        // new Thread(new SensorTask(sliderMoisture, labelMoistureAlarm, labelMoistureActuator, 20, 30, 25, "Moisture")).start();
        // new Thread(new SensorTask(sliderLight, labelLightAlarm, labelLightActuator, 20000, 40000, 30000, "Light")).start();

          // Start each sensor with respective config
        new Thread(new SensorTask(sliderTemp, labelTempAlarm, labelTempActuator, 15, 25, 20, "Temperature", false)).start();
        new Thread(new SensorTask(sliderHumidity, labelHumidityAlarm, labelHumidityActuator, 60, 80, 70, "Humidity", false)).start();
        new Thread(new SensorTask(sliderMoisture, labelMoistureAlarm, labelMoistureActuator, 20, 30, 25, "Moisture", false)).start();
        new Thread(new SensorTask(sliderLight, labelLightAlarm, labelLightActuator, 20000, 40000, 30000, "Light", true)).start();
    

    }



    
    
    
    class SensorTask implements Runnable {
        private final JSlider slider;
        private final JLabel alarmLabel;
        private final JLabel actuatorLabel;
        private final int minIdeal, maxIdeal, perfectValue;
        private final String type;
        private final boolean isKilo; // True for Light only

        public SensorTask(JSlider slider, JLabel alarmLabel, JLabel actuatorLabel,
                          int minIdeal, int maxIdeal, int perfectValue, String type, boolean isKilo) {
            this.slider = slider;
            this.alarmLabel = alarmLabel;
            this.actuatorLabel = actuatorLabel;
            this.minIdeal = minIdeal;
            this.maxIdeal = maxIdeal;
            this.perfectValue = perfectValue;
            this.type = type;
            this.isKilo = isKilo;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int sliderValue = slider.getValue(); // 10–50 for Light (kLux)
                    int actualValue = isKilo ? sliderValue * 1000 : sliderValue; // Convert to lux

                    // Logic: compare in actual scale (lux or raw)
                    if (actualValue >= minIdeal && actualValue <= maxIdeal) {
                        alarmLabel.setText("Normal");
                        alarmLabel.setBackground(Color.GREEN);
                        alarmLabel.setOpaque(true);

                        if (actualValue == perfectValue) {
                            actuatorLabel.setText(type + " Control Off");
                        }
                    } else {
                        // Out of ideal range
                        if (actualValue < minIdeal) {
                            alarmLabel.setText(type + " too Low!");
                            actuatorLabel.setText(getActuatorLabel("low"));
                        } else {
                            alarmLabel.setText(type + " too High!");
                            actuatorLabel.setText(getActuatorLabel("high"));
                        }

                        alarmLabel.setBackground(Color.RED);
                        alarmLabel.setOpaque(true);

                        // Auto-adjust slider toward perfectValue (using slider scale)
                        new Thread(() -> {
                            try {
                                int perfectSlider = isKilo ? perfectValue / 1000 : perfectValue;
                                while (slider.getValue() != perfectSlider) {
                                    int current = slider.getValue();
                                    slider.setValue(current < perfectSlider ? current + 1 : current - 1);
                                    Thread.sleep(3000);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }

                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private String getActuatorLabel(String direction) {
            return switch (type) {
                case "Temperature" -> direction.equals("low") ? "Heating On" : "Cooling On";
                case "Humidity" -> direction.equals("low") ? "Humidifier On" : "Ventilation On";
                case "Moisture" -> direction.equals("low") ? "Irrigation On" : "Aeration On";
                case "Light" -> direction.equals("low") ? "Brightening On" : "Dimming On";
                default -> "Control On";
            };
        }
    }
    
    
    
    
    


    /*
    class SensorTask implements Runnable {
        private final JSlider slider;
        private final JLabel alarmLabel;
        private final JLabel actuatorLabel;
        private final int minIdeal, maxIdeal, perfectValue;
        private final String type;

        public SensorTask(JSlider slider, JLabel alarmLabel, JLabel actuatorLabel, int minIdeal, int maxIdeal, int perfectValue, String type) {
            this.slider = slider;
            this.alarmLabel = alarmLabel;
            this.actuatorLabel = actuatorLabel;
            this.minIdeal = minIdeal;
            this.maxIdeal = maxIdeal;
            this.perfectValue = perfectValue;
            this.type = type;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int value = slider.getValue();

                    if (value >= minIdeal && value <= maxIdeal) {
                        alarmLabel.setText("Normal");
                        alarmLabel.setBackground(Color.GREEN);
                        alarmLabel.setOpaque(true);

                        if (value == perfectValue) {
                            actuatorLabel.setText(type + " Control Off");
                        }
                    } else {
                        // Alarm logic
                        if (value < minIdeal) {
                            alarmLabel.setText(type + " too Low!");
                            actuatorLabel.setText(getActuatorLabel("low"));
                        } else {
                            alarmLabel.setText(type + " too High!");
                            actuatorLabel.setText(getActuatorLabel("high"));
                        }

                        alarmLabel.setBackground(Color.RED);
                        alarmLabel.setOpaque(true);

                        // Adjust the slider toward the perfect value (1 unit per 3 seconds)
                        new Thread(() -> {
                            try {
                                while (slider.getValue() != perfectValue) {
                                    int current = slider.getValue();
                                    if (current < perfectValue) {
                                        slider.setValue(current + 1);
                                    } else {
                                        slider.setValue(current - 1);
                                    }
                                    Thread.sleep(3000); // wait 3 seconds
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }

                    Thread.sleep(500); // main sensor check every 0.5 sec
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private String getActuatorLabel(String direction) {
            switch (type) {
                case "Temperature":
                    return direction.equals("low") ? "Heating On" : "Cooling On";
                case "Humidity":
                    return direction.equals("low") ? "Humidifier On" : "Ventilation On";
                case "Moisture":
                    return direction.equals("low") ? "Irrigation On" : "Aeration On";
                case "Light":
                    return direction.equals("low") ? "Brightening On" : "Dimming On";
                default:
                    return "Control On";
            }
        }
    }
    */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        sliderTemp = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sliderHumidity = new javax.swing.JSlider();
        sliderMoisture = new javax.swing.JSlider();
        sliderLight = new javax.swing.JSlider();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelTempAlarm = new javax.swing.JLabel();
        labelTempActuator = new javax.swing.JLabel();
        labelHumidityAlarm = new javax.swing.JLabel();
        labelHumidityActuator = new javax.swing.JLabel();
        labelMoistureAlarm = new javax.swing.JLabel();
        labelLightAlarm = new javax.swing.JLabel();
        labelMoistureActuator = new javax.swing.JLabel();
        labelLightActuator = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("temerature"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(64, 107, 173));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 50));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Temprature (°C)");

        sliderTemp.setMajorTickSpacing(5);
        sliderTemp.setMaximum(30);
        sliderTemp.setMinimum(10);
        sliderTemp.setMinorTickSpacing(1);
        sliderTemp.setPaintLabels(true);
        sliderTemp.setPaintTicks(true);
        sliderTemp.setValue(20);
        sliderTemp.setName("sliderTemp"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Humidity (% RH)");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Moisture (% VWC)");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Light (Klux)");

        sliderHumidity.setMajorTickSpacing(5);
        sliderHumidity.setMaximum(90);
        sliderHumidity.setMinimum(50);
        sliderHumidity.setMinorTickSpacing(1);
        sliderHumidity.setPaintLabels(true);
        sliderHumidity.setPaintTicks(true);
        sliderHumidity.setValue(70);
        sliderHumidity.setName("sliderHumidity"); // NOI18N

        sliderMoisture.setMajorTickSpacing(5);
        sliderMoisture.setMaximum(40);
        sliderMoisture.setMinimum(10);
        sliderMoisture.setMinorTickSpacing(1);
        sliderMoisture.setPaintLabels(true);
        sliderMoisture.setPaintTicks(true);
        sliderMoisture.setValue(25);
        sliderMoisture.setName("sliderMoisture"); // NOI18N

        sliderLight.setMajorTickSpacing(5);
        sliderLight.setMaximum(50);
        sliderLight.setMinimum(10);
        sliderLight.setMinorTickSpacing(1);
        sliderLight.setPaintLabels(true);
        sliderLight.setPaintTicks(true);
        sliderLight.setValue(30);
        sliderLight.setName("sliderLight"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Alarm");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Actuator");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Alarm");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Actuator");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Alarm");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Actuator");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Alarm");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Actuator");

        labelTempAlarm.setBackground(new java.awt.Color(255, 255, 255));
        labelTempAlarm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTempAlarm.setForeground(new java.awt.Color(0, 153, 51));
        labelTempAlarm.setText("Normal");
        labelTempAlarm.setPreferredSize(new java.awt.Dimension(60, 16));

        labelTempActuator.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTempActuator.setText("Temperature Control Off");

        labelHumidityAlarm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelHumidityAlarm.setForeground(new java.awt.Color(0, 153, 51));
        labelHumidityAlarm.setText("Normal");

        labelHumidityActuator.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelHumidityActuator.setText("Humidity Control Off");

        labelMoistureAlarm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMoistureAlarm.setForeground(new java.awt.Color(0, 153, 51));
        labelMoistureAlarm.setText("Normal");

        labelLightAlarm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLightAlarm.setForeground(new java.awt.Color(0, 153, 51));
        labelLightAlarm.setText("Normal");

        labelMoistureActuator.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMoistureActuator.setText("Moisture Control Off");

        labelLightActuator.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelLightActuator.setText("Light Control Off");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))
                                .addGap(55, 55, 55))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(52, 52, 52))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(52, 52, 52))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(33, 33, 33)
                                        .addComponent(labelLightActuator, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelLightAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(sliderMoisture, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sliderHumidity, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sliderLight, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelMoistureAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel10)
                                                .addGap(39, 39, 39)
                                                .addComponent(labelMoistureActuator, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelHumidityAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8)
                                                .addGap(38, 38, 38)
                                                .addComponent(labelHumidityActuator, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(labelTempAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(42, 42, 42)
                                .addComponent(labelTempActuator, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(sliderTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTempActuator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6))
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(labelTempAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(sliderHumidity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(labelHumidityAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHumidityActuator, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(sliderMoisture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelMoistureActuator, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel9))
                    .addComponent(labelMoistureAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sliderLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(labelLightActuator, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLightAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelHumidityActuator;
    private javax.swing.JLabel labelHumidityAlarm;
    private javax.swing.JLabel labelLightActuator;
    private javax.swing.JLabel labelLightAlarm;
    private javax.swing.JLabel labelMoistureActuator;
    private javax.swing.JLabel labelMoistureAlarm;
    private javax.swing.JLabel labelTempActuator;
    private javax.swing.JLabel labelTempAlarm;
    private javax.swing.JSlider sliderHumidity;
    private javax.swing.JSlider sliderLight;
    private javax.swing.JSlider sliderMoisture;
    private javax.swing.JSlider sliderTemp;
    // End of variables declaration//GEN-END:variables
}

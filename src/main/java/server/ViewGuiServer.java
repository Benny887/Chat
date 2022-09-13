package server;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewGuiServer {
    private JFrame frame = new JFrame("Запуск сервера");
    private JTextArea dialogWindow = new JTextArea(10, 40);
    private JButton buttonStartServer = new JButton("Запустить сервер");
    private JButton buttonStopServer = new JButton("Остановить сервер");
    private JPanel panelButtons = new JPanel();
    private final server.Server server;

    public ViewGuiServer(server.Server server) {
        this.server = server;
    }

    protected void initFrameServer() {
        dialogWindow.setEditable(false);
        dialogWindow.setLineWrap(true);
        DefaultCaret caret = (DefaultCaret) dialogWindow.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        frame.add(new JScrollPane(dialogWindow), BorderLayout.CENTER);
        panelButtons.add(buttonStartServer);
        panelButtons.add(buttonStopServer);
        frame.add(panelButtons, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                server.stopServer();
                System.exit(0);
            }
        });
        frame.setVisible(true);
        server.startServer();
        buttonStartServer.setEnabled(false);
        buttonStartServer.addActionListener(e -> {
            server.startServer();
            if (Server.isIsServerStarted()) {
                buttonStopServer.setEnabled(true);
                buttonStartServer.setEnabled(false);
            }
        });
        buttonStopServer.addActionListener(e -> {
            server.stopServer();
            buttonStopServer.setEnabled(false);
            buttonStartServer.setEnabled(true);
        });
    }

    public void refreshDialogWindowServer(String serviceMessage) {
        dialogWindow.append(serviceMessage);
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class QueueSystem extends JFrame {
    private Queue<Ticket> queue;
    private JTextField cedulaField;
    private JTextArea queueStatus;
    private int ticketNumber;

    public QueueSystem() {
        queue = new LinkedList<>();
        ticketNumber = 1; // Inicializamos el número de ticket

        setTitle("Sistema de Espera");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.setBackground(new Color(34, 139, 34)); // Fondo verde

        JLabel welcomeLabel = new JLabel("Bienvenido");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.YELLOW); // Texto amarillo

        JLabel instructionLabel = new JLabel("Ingrese su número de cédula:");
        instructionLabel.setForeground(Color.WHITE); // Texto blanco

        cedulaField = new JTextField(20);

        JButton addButton = new JButton("Generar Ticket");
        addButton.setBackground(Color.LIGHT_GRAY); // Fondo gris claro
        addButton.setForeground(Color.BLACK); // Texto negro

        JButton processButton = new JButton("Procesar Ticket");
        processButton.setBackground(Color.RED); // Fondo rojo
        processButton.setForeground(Color.BLACK); // Texto negro

        JButton showQueueButton = new JButton("Mostrar Cola");
        showQueueButton.setBackground(Color.LIGHT_GRAY); // Fondo gris claro
        showQueueButton.setForeground(Color.BLACK); // Texto negro

        JButton nextElementButton = new JButton("Ver Siguiente");
        nextElementButton.setBackground(Color.LIGHT_GRAY); // Fondo gris claro
        nextElementButton.setForeground(Color.BLACK); // Texto negro

        JButton emptyQueueButton = new JButton("Vaciar Cola");
        emptyQueueButton.setBackground(Color.LIGHT_GRAY); // Fondo gris claro
        emptyQueueButton.setForeground(Color.BLACK); // Texto negro

        queueStatus = new JTextArea(10, 30);
        queueStatus.setEditable(false);
        queueStatus.setBackground(Color.WHITE); // Fondo blanco
        queueStatus.setForeground(Color.BLACK); // Texto negro

        inputPanel.add(welcomeLabel);
        inputPanel.add(new JLabel(""));
        inputPanel.add(instructionLabel);
        inputPanel.add(cedulaField);
        inputPanel.add(addButton);
        inputPanel.add(processButton);
        inputPanel.add(showQueueButton);
        inputPanel.add(nextElementButton);
        inputPanel.add(emptyQueueButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(queueStatus), BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToQueue();
            }
        });

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processQueue();
            }
        });

        showQueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQueue();
            }
        });

        nextElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextElement();
            }
        });

        emptyQueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyQueue();
            }
        });
    }

    private void addToQueue() {
        String cedula = cedulaField.getText();
        if (!cedula.isEmpty()) {
            Ticket ticket = new Ticket("A", ticketNumber++); // Usamos prefijo "A"
            queue.add(ticket);
            cedulaField.setText("");
            updateQueueStatus();
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un número de cédula válido.");
        }
    }

    private void processQueue() {
        if (!queue.isEmpty()) {
            queue.poll();
            updateQueueStatus();
        } else {
            JOptionPane.showMessageDialog(this, "La cola está vacía.");
        }
    }

    private void showQueue() {
        updateQueueStatus();
    }

    private void showNextElement() {
        if (!queue.isEmpty()) {
            Ticket next = queue.peek();
            JOptionPane.showMessageDialog(this, "El siguiente en la cola es: " + next.getTicketId());
        } else {
            JOptionPane.showMessageDialog(this, "La cola está vacía.");
        }
    }

    private void emptyQueue() {
        queue.clear();
        updateQueueStatus();
    }

    private void updateQueueStatus() {
        queueStatus.setText("Estado de la cola:\n");
        for (Ticket ticket : queue) {
            queueStatus.append(ticket.getTicketId() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QueueSystem().setVisible(true);
            }
        });
    }
}

class Ticket {
    private String prefix;
    private int number;

    public Ticket(String prefix, int number) {
        this.prefix = prefix;
        this.number = number;
    }

    public String getTicketId() {
        return prefix + String.format("%03d", number);
    }
}

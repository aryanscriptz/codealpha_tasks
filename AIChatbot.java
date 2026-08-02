import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class AIChatbot extends JFrame implements ActionListener {

    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;

    HashMap<String, String> responses;

    public AIChatbot() {

        setTitle("AI Chatbot");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 15));

        JScrollPane scroll = new JScrollPane(chatArea);

        JPanel panel = new JPanel(new BorderLayout());

        inputField = new JTextField();
        sendButton = new JButton("Send");

        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        add(scroll, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        responses = new HashMap<>();

        responses.put("hello", "Hello! How can I help you?");
        responses.put("hi", "Hi! Nice to meet you.");
        responses.put("how are you", "I'm doing great. Thank you!");
        responses.put("your name", "I'm a Java AI Chatbot.");
        responses.put("java", "Java is an object-oriented programming language.");
        responses.put("python", "Python is a simple and powerful programming language.");
        responses.put("oop", "OOP stands for Object-Oriented Programming.");
        responses.put("college", "Study consistently and practice coding daily.");
        responses.put("project", "This chatbot is built using Java Swing and rule-based NLP.");
        responses.put("bye", "Goodbye! Have a nice day.");
    }

    public void actionPerformed(ActionEvent e) {

        String user = inputField.getText().trim();

        if(user.isEmpty())
            return;

        chatArea.append("You : " + user + "\n");

        String botReply = getResponse(user);

        chatArea.append("Bot : " + botReply + "\n\n");

        inputField.setText("");
    }

    public String getResponse(String message) {

        message = message.toLowerCase();

        for(String key : responses.keySet()) {

            if(message.contains(key))
                return responses.get(key);
        }

        return "Sorry, I don't understand. Please ask another question.";
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            AIChatbot bot = new AIChatbot();
            bot.setVisible(true);

        });

    }
}
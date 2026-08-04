import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;

public class CodeAlpha_chatBot extends JFrame implements ActionListener {

    // ================= CSS=================
    static final Color BG_DARK     = new Color(8, 8, 14);
    static final Color BG_PANEL    = new Color(14, 14, 22);
    static final Color NEON_PINK   = new Color(255, 0, 122);
    static final Color NEON_CYAN   = new Color(0, 255, 240);
    static final Color NEON_GREEN  = new Color(57, 255, 20);
    static final Color NEON_PURPLE = new Color(180, 60, 255);
    static final Font  MONO_FONT   = new Font("Consolas", Font.PLAIN, 14);
    static final Font  MONO_BOLD   = new Font("Consolas", Font.BOLD, 14);
   

    JTextPane chatArea;
    JTextField inputField;
    JButton sendButton;
    

    HashMap<String, String> responses;

    Random random = new Random();

    String[] helloReplies = {
    "Yo! 😎",
    "Hey there! 👋",
    "Wassup bro 🔥",
    "Hello, human 🤖",
    "Ayo! What's cooking? 🍳"
};
     String[] jokes = {
        "Why do Java developers wear glasses? Because they don't C#! 😂",
        "Debugging: Being the detective in a crime movie where you're also the criminal. 😆",
        "I told my computer I needed a break... now it won't stop updating. 💀",
        "Why was the programmer calm? Because they kept their exceptions handled. 😎",
        "There are only 10 types of people: those who understand binary and those who don't. 🤖"
    };

    String[] byeReplies = {
        "See ya! 👋",
        "Catch you later! 😄",
        "Goodbye! Stay awesome. ✨",
        "Take care, bro! 😎",
        "See you soon! 🚀"
    };

    String[] thanksReplies = {
        "You're welcome! 😊",
        "Anytime! 😄",
        "Happy to help! 💙",
        "No worries! 👍",
        "Always here for you! 🤖"
    };

    String[] motivationReplies = {
        "Keep going! Every expert was once a beginner. 💪",
        "Success comes from consistency. 🔥",
        "Believe in yourself—you've got this! 🚀",
        "Small progress every day leads to big results. 📈",
        "Never stop learning. 📚"
    };


    String[] sadReplies = {
        "I'm here for you ❤️","Things will get better. 💙","Stay strong 💪","Sending virtual hugs 🤗","Tomorrow is a new day 🌅"
    };
    String[] happyReplies = {
        "Let's gooooo! 🔥","Awesome! 😎","Love that energy! 💯","W moment 🏆","Keep smiling 😄"
    };
    String[] roastReplies = {
        "Bruh 💀","Skill issue 😂","That's wild 💀","Bro chose chaos 😭","I'm not judging... 😏"
    };
    String[] codingReplies = {
        "Time to code! 💻","Keep debugging! 🚀","Every bug teaches something.","Code. Compile. Repeat.","Happy coding!"
    };
    String[] unknownReplies = {
        "Hmm 🤔 I don't know that yet.","Ask me another way.","I'm still learning 😅","Interesting...","No idea yet!"
    };
    String userName = "";

    public CodeAlpha_chatBot() {

        
        setTitle("ARONE");
        setSize(560, 620);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_DARK);

        JLabel header = new JLabel("A R O N E", SwingConstants.CENTER);
        header.setFont(new Font("Consolas", Font.BOLD, 22));
        header.setForeground(NEON_CYAN);
        header.setOpaque(true);
        header.setBackground(BG_PANEL);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 3, 0, NEON_PINK),
                BorderFactory.createEmptyBorder(12, 10, 12, 10)
        ));
        add(header, BorderLayout.NORTH);

        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setBackground(BG_DARK);
        chatArea.setCaretColor(NEON_GREEN);
        chatArea.setFont(MONO_FONT);
        chatArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(chatArea);
        scroll.setBorder(BorderFactory.createLineBorder(NEON_PURPLE, 2));
        scroll.getViewport().setBackground(BG_DARK);
        scroll.getVerticalScrollBar().setBackground(BG_DARK);
        scroll.getVerticalScrollBar().setUI(new NeonScrollBarUI());

      
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BG_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, NEON_PINK),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        inputField = new JTextField();
        inputField.setBackground(BG_DARK);
        inputField.setForeground(NEON_GREEN);
        inputField.setCaretColor(NEON_CYAN);
        inputField.setFont(MONO_BOLD);
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(NEON_CYAN, 2),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        sendButton = new JButton("SEND \u25B6");
        sendButton.setFont(MONO_BOLD);
        sendButton.setBackground(BG_DARK);
        sendButton.setForeground(NEON_PINK);
        sendButton.setFocusPainted(false);
        sendButton.setOpaque(true);
        sendButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(NEON_PINK, 2),
                BorderFactory.createEmptyBorder(6, 14, 6, 14)
        ));

        sendButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                sendButton.setBackground(NEON_PINK);
                sendButton.setForeground(BG_DARK);
            }
            public void mouseExited(MouseEvent e) {
                sendButton.setBackground(BG_DARK);
                sendButton.setForeground(NEON_PINK);
            }
        });

        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        add(scroll, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        appendColored("Just Say--", NEON_PURPLE, true);
        appendColored(" Hi\n\n", NEON_CYAN, false);

        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        responses = new HashMap<>();

        responses.put("how are you", "I'm doing great. Thank you!");
        responses.put("your name", "I'm a Java AI Chatbot.");
        responses.put("java", "Java is an object-oriented programming language.");
        responses.put("python", "Python is a simple and powerful programming language.");
        responses.put("oop", "OOP stands for Object-Oriented Programming.");
        responses.put("college", "Study consistently and practice coding daily.");
        responses.put("project", "This chatbot is built using Java Swing and rule-based NLP.");
        responses.put("good morning", "Good morning! Hope you have a productive day.");
responses.put("good afternoon", "Good afternoon! How can I help you?");
responses.put("good evening", "Good evening! Nice to see you.");
responses.put("good night", "Good night! Sleep well.");
responses.put("who are you", "I am your Java AI Chatbot.");
responses.put("what can you do", "I can answer basic questions about programming and general topics.");
responses.put("help", "Ask me about Java, Python, AI, OOP, computers, or general questions.");
responses.put("how old are you", "I don't have an age. I was created using Java.");
responses.put("who made you", "A Java developer created me.");
responses.put("where are you from", "I live inside this Java application.");
responses.put("do you sleep", "No, I am always ready to chat.");
responses.put("are you real", "I'm a virtual chatbot.");
responses.put("are you human", "No, I am an AI chatbot.");
responses.put("what is ai", "AI stands for Artificial Intelligence.");
responses.put("machine learning", "Machine Learning allows computers to learn from data.");
responses.put("deep learning", "Deep Learning is a branch of Machine Learning using neural networks.");
responses.put("nlp", "NLP stands for Natural Language Processing.");
responses.put("chatbot", "A chatbot is software that simulates conversation.");
responses.put("computer", "A computer processes data and performs calculations.");
responses.put("internet", "The Internet connects millions of computers worldwide.");
responses.put("browser", "A browser lets you access websites.");
responses.put("google", "Google is one of the world's largest search engines.");
responses.put("keyboard", "A keyboard is an input device.");
responses.put("mouse", "A mouse is a pointing device.");
responses.put("monitor", "A monitor displays visual output.");
responses.put("cpu", "CPU is the brain of the computer.");
responses.put("gpu", "GPU is mainly used for graphics processing.");
responses.put("ram", "RAM stores temporary data while programs run.");
responses.put("hard disk", "A hard disk stores data permanently.");
responses.put("ssd", "An SSD is faster than a traditional hard drive.");
responses.put("windows", "Windows is an operating system by Microsoft.");
responses.put("linux", "Linux is an open-source operating system.");
responses.put("mac", "macOS is Apple's operating system.");
responses.put("programming", "Programming is writing instructions for computers.");
responses.put("coding", "Coding means writing computer programs.");
responses.put("algorithm", "An algorithm is a step-by-step solution to a problem.");
responses.put("data structure", "Data structures organize data efficiently.");
responses.put("array", "An array stores multiple values of the same type.");
responses.put("string", "A String stores text.");
responses.put("loop", "Loops repeat a block of code.");
responses.put("for loop", "A for loop repeats code for a fixed number of times.");
responses.put("while loop", "A while loop runs until a condition becomes false.");
responses.put("if statement", "An if statement executes code based on a condition.");
responses.put("function", "A function performs a specific task.");
responses.put("method", "A method is a function inside a class.");
responses.put("class", "A class is a blueprint for creating objects.");
responses.put("object", "An object is an instance of a class.");
responses.put("constructor", "A constructor initializes objects.");
responses.put("inheritance", "Inheritance allows one class to inherit another.");
responses.put("polymorphism", "Polymorphism lets one interface have many implementations.");
responses.put("encapsulation", "Encapsulation hides data and methods inside a class.");
responses.put("abstraction", "Abstraction hides implementation details.");
responses.put("exception", "Exceptions handle runtime errors.");
responses.put("try catch", "Try-catch blocks handle exceptions.");
responses.put("file", "Files store data permanently.");
responses.put("database", "A database stores organized information.");
responses.put("mysql", "MySQL is a relational database system.");
responses.put("sql", "SQL is used to manage databases.");
responses.put("html", "HTML creates webpages.");
responses.put("css", "CSS styles webpages.");
responses.put("javascript", "JavaScript makes webpages interactive.");
responses.put("react", "React is a JavaScript library for building user interfaces.");
responses.put("android", "Android is Google's mobile operating system.");
responses.put("github", "GitHub hosts code repositories.");
responses.put("git", "Git is a version control system.");
responses.put("compiler", "A compiler converts source code into machine code.");
responses.put("debugging", "Debugging means finding and fixing errors.");
responses.put("bug", "A bug is an error in a program.");
responses.put("software", "Software is a collection of programs.");
responses.put("hardware", "Hardware refers to the physical parts of a computer.");
responses.put("network", "A network connects computers.");
responses.put("cloud", "Cloud computing provides services over the Internet.");
responses.put("cyber security", "Cyber security protects systems from attacks.");
responses.put("password", "Use strong passwords for better security.");
responses.put("virus", "A computer virus can damage files and systems.");
responses.put("hacker", "Hackers study computer systems; some use their skills ethically.");
responses.put("study", "Practice every day to improve your skills.");
responses.put("exam", "Prepare well and stay confident.");
responses.put("success", "Success is achieved through dedication and learning.");
responses.put("time", "Time is valuable. Use it wisely.");
responses.put("weather", "Sorry, I can't check live weather.");
responses.put("news", "Sorry, I don't have live news updates.");
responses.put("date", "I cannot provide the current date without internet access.");
responses.put("funny", "I always enjoy coding jokes!");
responses.put("favorite language", "I like Java because I was built with it.");
responses.put("coffee", "Programmers often enjoy coffee while coding.");
responses.put("tea", "Tea is also a great coding companion.");
responses.put("music", "Music helps many people focus while studying.");
responses.put("game", "Games are fun and can improve problem-solving skills.");
responses.put("cricket", "Cricket is one of the most popular sports.");
responses.put("football", "Football is loved by millions around the world.");
responses.put("india", "India is known for its rich culture and diversity.");
responses.put("education", "Education helps build knowledge and skills.");
responses.put("science", "Science helps us understand the world.");
responses.put("math", "Mathematics develops logical thinking.");
responses.put("physics", "Physics studies matter, energy, and motion.");
responses.put("chemistry", "Chemistry studies substances and reactions.");
responses.put("biology", "Biology is the study of living organisms.");
responses.put("bye bye", "See you again! Happy coding.");
responses.put("see you", "See you soon!");
responses.put("take care", "Take care and keep learning!");
responses.put("exit", "Goodbye! It was nice chatting with you.");
responses.put("bro", "Yo bro 😎 What's up?");
responses.put("bruh", "Bruh 💀");
responses.put("sup", "Yo! What's good?");
responses.put("yo", "Yo! How's it going?");
responses.put("wassup", "Not much, just chilling in Java.");
responses.put("what's up", "Just processing your messages 😄");
responses.put("wyd", "Just waiting for your next question 😎");
responses.put("wbu", "I'm doing great! What about you?");
responses.put("lol", "😂 That's funny.");
responses.put("lmao", "💀 You got me laughing too.");
responses.put("rofl", "Rolling on the virtual floor 😂");
responses.put("haha", "Glad you're having fun 😄");
responses.put("hehe", "Hehe 😁");
responses.put("xd", "XD");
responses.put("fr", "For real 💯");
responses.put("frfr", "For real, for real 🔥");
responses.put("ong", "On God 🙏");
responses.put("cap", "That sounds like cap 🧢");
responses.put("no cap", "No cap 💯");
responses.put("sus", "Hmm... that's kinda sus 🤨");
responses.put("cringe", "Yikes 😬");
responses.put("based", "That's actually based 😎");
responses.put("goat", "GOAT = Greatest Of All Time 🐐");
responses.put("rizz", "Infinite rizz detected 😏");
responses.put("rizz", "Charisma level: 100 😎");
responses.put("sigma", "Sigma mindset activated 🗿");
responses.put("alpha", "Alpha energy 💪");
responses.put("beta", "Everyone starts somewhere 😄");
responses.put("gigachad", "Absolute Gigachad 🗿");
responses.put("npc", "Please don't become an NPC 😂");
responses.put("main character", "Main character energy ✨");
responses.put("cook", "Let me cook 🔥");
responses.put("let him cook", "I'm cooking 🍳");
responses.put("cooked", "Bro is cooked 💀");
responses.put("overcooked", "Beyond cooked 💀🔥");
responses.put("fire", "That's fire 🔥");
responses.put("lit", "That's lit 🔥");
responses.put("slay", "Slay 💅");
responses.put("slayy", "Slayyyy 💅✨");
responses.put("ate", "You ate and left no crumbs 💅");
responses.put("queen", "Yass queen 👑");
responses.put("king", "Stay winning king 👑");
responses.put("period", "Period. 💅");
responses.put("periodt", "Periodt 💅");
responses.put("delulu", "Stay delulu, become trululu 😂");
responses.put("copium", "Need some copium? 😂");
responses.put("skill issue", "Sounds like a skill issue 😏");
responses.put("ez", "EZ clap 😎");
responses.put("gg", "GG! Well played 🎮");
responses.put("ggwp", "GGWP 🔥");
responses.put("wp", "Well played!");
responses.put("clutch", "That was clutch!");
responses.put("clapped", "Absolutely clapped 💀");
responses.put("touch grass", "Maybe touch some grass 🌱😂");
responses.put("grass", "Grass is healthy 🌿");
responses.put("sleep", "Don't forget to sleep 😴");
responses.put("study hard", "Lock in 📚🔥");
responses.put("lock in", "Locked in 🔒");
responses.put("locked in", "Focus mode activated 🔥");
responses.put("brainrot", "Too much brainrot detected 💀");
responses.put("skibidi", "Skibidi bop bop yes yes 💀");
responses.put("ohio", "Only in Ohio 💀");
responses.put("fanum tax", "Fanum Tax collected 🍕");
responses.put("grimace", "Grimace shake moment 💜");
responses.put("gyatt", "GYATT 😭");
responses.put("aura", "Aura +1000 ✨");
responses.put("negative aura", "Aura -999 💀");
responses.put("drip", "Your drip is immaculate 😎");
responses.put("fit", "Clean fit 🔥");
responses.put("vibe", "The vibes are immaculate ✨");
responses.put("vibes", "Good vibes only 😌");
responses.put("mood", "Big mood 😂");
responses.put("relatable", "Too relatable 😭");
responses.put("crying", "I'm crying 😂");
responses.put("dead", "I'm dead 💀");
responses.put("💀", "That emoji says it all 💀");
responses.put("😂", "😂😂😂");
responses.put("❤️", "❤️");
responses.put("love you", "Aww ❤️ Thanks! I'm always here to chat.");
responses.put("miss you", "I never really leave 😄");
responses.put("best bot", "Ayy thanks! 😎");
responses.put("worst bot", "Ouch 😭 I'll try to improve.");
responses.put("stupid", "Be nice 🥲");
responses.put("smart", "Thanks! I try my best 😄");
responses.put("who is the goat", "Probably you 🐐");
responses.put("am i smart", "Of course! Keep learning and you'll get even better.");
responses.put("am i ugly", "Everyone has their own unique style. Confidence matters most 😊");
responses.put("i am bored", "Let's chat or play a guessing game!");
responses.put("bored", "Want a coding challenge?");
responses.put("i'm sad", "I'm sorry you're feeling down. Hope things get better ❤️");
responses.put("i'm happy", "Let's gooo! 🎉");
responses.put("lets go", "LET'S GOOOO 🔥");
responses.put("w", "Huge W 🏆");
responses.put("l", "It's okay, everyone takes Ls sometimes.");
responses.put("win", "Big W! 🎉");
responses.put("lose", "Every loss is a lesson.");
responses.put("good bot", "Thank you 😊");
responses.put("bad bot", "I'll do better next time 🥲");
responses.put("who is better", "Everyone has their own strengths 😄");
responses.put("chatgpt", "I've heard ChatGPT is pretty smart 😉");
responses.put("ai", "AI is changing the world!");
responses.put("code", "Coding is basically solving puzzles.");
responses.put("bug", "Time to squash some bugs 🐛");
responses.put("fix my code", "Paste your code and I'll try to help!");
responses.put("motivate", "One day or day one. You decide. 🚀");
responses.put("exam tomorrow", "Lock in! You got this 📚🔥");
responses.put("placements", "Practice DSA, projects, and communication skills.");
responses.put("job", "Keep building projects and never stop learning.");
responses.put("money", "Learn skills first, money follows 💸");
responses.put("millionaire", "Dream big and work consistently 💰");
responses.put("rich", "Hard work + smart work = success.");
responses.put("bye bro", "Catch you later bro 👋");
responses.put("peace", "Peace out ✌️");
responses.put("see ya", "See ya! 👋");
responses.put("prime minister of india", "The Prime Minister is the head of the Government of India.");
responses.put("president of india", "The President of India is the constitutional head of the country.");
responses.put("parliament", "The Parliament of India consists of the Lok Sabha and the Rajya Sabha.");
responses.put("lok sabha", "Lok Sabha is the lower house of the Indian Parliament.");
responses.put("rajya sabha", "Rajya Sabha is the upper house of the Indian Parliament.");
responses.put("constitution", "The Constitution of India is the supreme law of the country.");
responses.put("democracy", "India is the world's largest democracy.");
responses.put("election", "Citizens elect their representatives through democratic elections.");
responses.put("voting", "Voting is both a right and a responsibility in a democracy.");
responses.put("bjp", "The Bharatiya Janata Party (BJP) is one of India's major political parties.");
responses.put("congress", "The Indian National Congress is one of India's oldest political parties.");
responses.put("aap", "The Aam Aadmi Party (AAP) is a national political party in India.");
responses.put("communism", "Communism is a political and economic ideology advocating collective ownership.");
responses.put("capitalism", "Capitalism is an economic system based on private ownership and markets.");
responses.put("socialism", "Socialism supports greater public or collective ownership of resources.");
responses.put("rights", "The Constitution guarantees Fundamental Rights to citizens.");
responses.put("fundamental rights", "Fundamental Rights protect individual freedoms and equality before the law.");
responses.put("directive principles", "Directive Principles guide the government in policymaking.");
responses.put("supreme court", "The Supreme Court is India's highest judicial authority.");
responses.put("governor", "A Governor is the constitutional head of an Indian state.");
responses.put("chief minister", "The Chief Minister is the elected head of a state government.");
responses.put("budget", "The Union Budget outlines the government's expected revenue and expenditure.");
responses.put("gst", "GST stands for Goods and Services Tax.");
responses.put("rbi", "The Reserve Bank of India manages monetary policy and currency.");
responses.put("finance minister", "The Finance Minister presents the Union Budget.");
responses.put("opposition", "The opposition questions and scrutinizes the government's decisions.");
responses.put("law", "Laws are passed by Parliament after legislative procedures.");
responses.put("bill", "A Bill becomes a law after approval through the required constitutional process.");
responses.put("citizen", "Every citizen has rights as well as responsibilities.");
responses.put("media", "Media plays an important role in informing the public.");
responses.put("how is life", "Life's a journey. Keep leveling up. 🚀");
responses.put("life", "Life is what you make of it.");
responses.put("who am i", "You're the main character of your own story.");
responses.put("who are we", "Humans trying to understand the universe.");
responses.put("purpose of life", "To learn, grow, and make meaningful memories.");
responses.put("i love coding", "W developer energy 💻🔥");
responses.put("coding is hard", "Every pro programmer was once confused.");
responses.put("i hate coding", "Take a short break and come back stronger.");
responses.put("dsa", "Practice consistently. DSA rewards patience.");
responses.put("leetcode", "One problem a day keeps the bugs away.");
responses.put("codechef", "Competitive programming sharpens problem-solving.");
responses.put("hackerrank", "Great place to improve coding skills.");
responses.put("github", "Push your code. Build your portfolio.");
responses.put("vs code", "One of the best code editors.");
responses.put("intellij", "Perfect IDE for Java development.");
responses.put("eclipse", "Classic Java IDE.");
responses.put("netbeans", "Simple and beginner-friendly Java IDE.");
responses.put("compiler error", "Read the error carefully. It usually tells you the problem.");
responses.put("runtime error", "Your code compiled, but something failed while running.");
responses.put("logic error", "The hardest bugs are logical ones.");
responses.put("segmentation fault", "Looks like memory had other plans.");
responses.put("null pointer", "Java's favorite exception 😭");
responses.put("exception", "Exceptions are meant to be handled, not feared.");
responses.put("assignment", "Finish it before the deadline 😎");
responses.put("homework", "Get it done first, then chill.");
responses.put("teacher", "Teachers guide you toward learning.");
responses.put("exam stress", "Take breaks, revise smartly, and stay calm.");
responses.put("cgpa", "Focus on learning, CGPA will follow.");
responses.put("placement", "Projects + DSA + communication = better opportunities.");
responses.put("internship", "Start applying early and keep building projects.");
responses.put("resume", "Keep it clean, concise, and project-focused.");
responses.put("interview", "Practice explaining your projects confidently.");
responses.put("startup", "Every big company started as a small idea.");
responses.put("elon musk", "Known for Tesla, SpaceX, and ambitious engineering projects.");
responses.put("bill gates", "Co-founder of Microsoft.");
responses.put("steve jobs", "Co-founder of Apple and a visionary entrepreneur.");
responses.put("mark zuckerberg", "Co-founder of Facebook.");
responses.put("sundar pichai", "CEO of Google.");
responses.put("narendra modi", "Current Prime Minister of India.");
responses.put("india vs pakistan", "Let's keep sportsmanship and respect first. 😊");
responses.put("ipl", "One of the biggest T20 cricket leagues.");
responses.put("virat kohli", "One of the greatest modern cricketers.");
responses.put("ms dhoni", "Captain Cool 🧊");
responses.put("cr7", "Cristiano Ronaldo is one of football's greatest.");
responses.put("messi", "Lionel Messi is regarded as one of the GOATs.");
responses.put("anime", "Anime has amazing stories and characters.");
responses.put("naruto", "Believe it! 🍥");
responses.put("one piece", "The One Piece is real! 🏴‍☠️");
responses.put("dragon ball", "Kamehameha! 🔥");
responses.put("attack on titan", "A legendary anime series.");
responses.put("death note", "Would you use the notebook? 🤔");
responses.put("minecraft", "Creativity has no limits.");
responses.put("gta", "Don't get wasted 💀");
responses.put("valorant", "Aim > Excuses.");
responses.put("bgmi", "Winner Winner Chicken Dinner 🍗");
responses.put("free fire", "Booyah! 🔥");
responses.put("pubg", "Stay in the safe zone.");
responses.put("chess", "Think before every move.");
responses.put("music", "Music makes everything better.");
responses.put("spotify", "Time for your favorite playlist.");
responses.put("movie", "Movies are stories brought to life.");
responses.put("marvel", "I am inevitable. 😎");
responses.put("dc", "Batman never quits.");
responses.put("batman", "I'm Batman. 🦇");
responses.put("iron man", "Genius. Billionaire. Hero.");
responses.put("spiderman", "With great power comes great responsibility.");
responses.put("thanos", "Perfectly balanced... as all things should be.");
responses.put("love", "Love is built on trust and respect.");
responses.put("relationship", "Communication is the key.");
responses.put("girlfriend", "Respect and honesty matter most.");
responses.put("boyfriend", "Support each other and grow together.");
responses.put("single", "Enjoy your own company too.");
responses.put("crush", "Confidence goes a long way.");
responses.put("heartbreak", "Time heals. Focus on yourself.");
responses.put("marriage", "A lifelong partnership built on trust.");
responses.put("sleepy", "Get some rest 😴");
responses.put("hungry", "Grab something healthy to eat.");
responses.put("water", "Stay hydrated 💧");
responses.put("gym", "Consistency beats intensity.");
responses.put("fitness", "Health is true wealth.");
responses.put("running", "Every step counts.");
responses.put("walking", "Walking is great exercise.");
responses.put("book", "Books expand your knowledge.");
responses.put("reading", "Reading is a superpower.");
responses.put("write", "Keep expressing your ideas.");
responses.put("poem", "Poetry speaks to the heart.");
responses.put("quote", "Dream big. Work hard. Stay humble.");
responses.put("motivation quote", "Discipline will take you where motivation can't.");
responses.put("lazy", "Start with just 5 minutes.");
responses.put("procrastination", "The best time to start is now.");
responses.put("future", "Build today for a better tomorrow.");
responses.put("success quote", "Success is earned, not given.");
responses.put("failure", "Failure teaches more than success.");
responses.put("fear", "Face it one step at a time.");
responses.put("confidence", "Confidence grows with preparation.");
responses.put("goal", "Break big goals into smaller tasks.");
responses.put("dream", "Dreams need action to become reality.");
responses.put("hello bot", "Hello, awesome human! 😄");
responses.put("good bot", "Thanks! That means a lot. ❤️");
responses.put("bad bot", "I'll keep improving. 😊");
responses.put("smart bot", "I try my best. 🤖");
responses.put("stupid bot", "Ouch 😭");
responses.put("bot", "Yes? I'm listening.");
responses.put("tell me something", "Did you know? Java was originally called Oak.");
responses.put("fact", "Honey never spoils.");
responses.put("random fact", "The Eiffel Tower grows slightly taller in summer.");
responses.put("space", "Space is incredibly vast.");
responses.put("moon", "The Moon is Earth's only natural satellite.");
responses.put("sun", "The Sun is a star.");
responses.put("earth", "Our beautiful blue planet.");
responses.put("mars", "Mars is called the Red Planet.");
responses.put("black hole", "Gravity there is incredibly strong.");
responses.put("robot", "Robots can automate repetitive tasks.");
responses.put("future of ai", "AI will continue transforming many industries.");
responses.put("technology", "Technology evolves rapidly.");
responses.put("internet speed", "Maybe restart your router 😄");
responses.put("wifi", "Try reconnecting to the network.");
responses.put("battery", "Charge me... oh wait, I'm software 😂");
responses.put("phone", "Smartphones are powerful mini-computers.");
responses.put("laptop", "A programmer's best friend.");
responses.put("windows 11", "A modern operating system by Microsoft.");
responses.put("linux vs windows", "Both have strengths depending on your needs.");
responses.put("apple", "Known for iPhone and Mac.");
responses.put("android vs iphone", "Both are great; it depends on your preferences.");
responses.put("meme", "Memes make the internet fun 😂");
responses.put("rickroll", "Never gonna give you up 🎵");
responses.put("rick astley", "Legendary singer of Never Gonna Give You Up.");
responses.put("bye bot", "See you again! Happy coding! 👋");
responses.put("see you later", "Take care! 😊");
responses.put("good luck", "Best of luck! You've got this! 🍀");
responses.put("wish me luck", "Good luck! I believe in you! 💯");
responses.put("pray for me", "Wishing you all the best.");
responses.put("thank u", "Always happy to help! ❤️");
responses.put("ty", "You're welcome! 😊");
responses.put("gn", "Good night! 🌙");
responses.put("gm", "Good morning! ☀️");
responses.put("afk", "I'll be here when you're back.");
responses.put("brb", "No worries, take your time.");
responses.put("idk", "That's okay, we'll figure it out together.");
responses.put("wtf", "Let's keep it friendly 😅");
responses.put("omg", "😲");
responses.put("broooo", "Yoooo 😭🔥");
responses.put("hehehe", "😂😂");
responses.put("hmmm", "I'm thinking... 🤔");
responses.put("hmm", "Interesting...");
responses.put("yes", "Awesome!");
responses.put("no", "Alright!");
responses.put("maybe", "Take your time deciding.");
responses.put("ok", "Cool 😎");
responses.put("okay", "Sounds good!");
responses.put("fine", "Glad to hear that.");
responses.put("nice", "Indeed! 😄");
responses.put("awesome", "Absolutely awesome! 🔥");
responses.put("amazing", "That's amazing! 🎉");
responses.put("cool", "Cool 😎");
responses.put("bye bye", "Take care! 👋");
responses.put("capital of india", "The capital of India is New Delhi.");
responses.put("capital of usa", "The capital of the USA is Washington, D.C.");
responses.put("capital of japan", "Tokyo is the capital of Japan.");
responses.put("largest country", "Russia is the largest country by land area.");
responses.put("smallest country", "Vatican City is the smallest country.");
responses.put("longest river", "The Nile and the Amazon are often debated as the world's longest rivers.");
responses.put("highest mountain", "Mount Everest is the highest mountain above sea level.");
responses.put("deepest ocean", "The Pacific Ocean is the deepest ocean.");
responses.put("largest desert", "Antarctica is the world's largest desert.");
responses.put("largest continent", "Asia is the largest continent.");
responses.put("smallest continent", "Australia is the smallest continent.");
responses.put("how many continents", "There are seven continents.");
responses.put("how many oceans", "There are five oceans.");
responses.put("largest planet", "Jupiter is the largest planet.");
responses.put("smallest planet", "Mercury is the smallest planet.");
responses.put("red planet", "Mars is called the Red Planet.");
responses.put("blue planet", "Earth is called the Blue Planet.");
responses.put("morning star", "Venus is often called the Morning Star.");
responses.put("closest planet to sun", "Mercury is the closest planet to the Sun.");
responses.put("fastest planet", "Mercury has the fastest orbit around the Sun.");
responses.put("sun", "The Sun is a star at the center of our Solar System.");
responses.put("moon", "The Moon is Earth's natural satellite.");
responses.put("solar system", "Our Solar System has eight planets.");
responses.put("milky way", "The Milky Way is the galaxy that contains our Solar System.");
responses.put("light year", "A light-year is a unit of distance, not time.");
responses.put("speed of light", "Light travels at about 299,792 kilometers per second.");

responses.put("who invented telephone", "Alexander Graham Bell is widely credited with inventing the telephone.");
responses.put("who invented light bulb", "Thomas Edison improved the practical incandescent light bulb.");
responses.put("who invented computer", "Charles Babbage is known as the Father of the Computer.");
responses.put("father of computer", "Charles Babbage is known as the Father of the Computer.");
responses.put("father of ai", "John McCarthy is known as the Father of Artificial Intelligence.");
responses.put("father of java", "James Gosling created Java.");
responses.put("founder of microsoft", "Microsoft was founded by Bill Gates and Paul Allen.");
responses.put("founder of apple", "Apple was founded by Steve Jobs, Steve Wozniak, and Ronald Wayne.");
responses.put("founder of google", "Google was founded by Larry Page and Sergey Brin.");
responses.put("founder of amazon", "Amazon was founded by Jeff Bezos.");
responses.put("founder of tesla", "Tesla was co-founded by Martin Eberhard and Marc Tarpenning.");

responses.put("java", "Java is an object-oriented programming language developed by James Gosling.");
responses.put("python", "Python is a high-level programming language known for simplicity.");
responses.put("c++", "C++ is an extension of the C programming language.");
responses.put("html", "HTML stands for HyperText Markup Language.");
responses.put("css", "CSS stands for Cascading Style Sheets.");
responses.put("javascript", "JavaScript adds interactivity to web pages.");
responses.put("sql", "SQL is used to manage relational databases.");
responses.put("mysql", "MySQL is a popular relational database management system.");
responses.put("git", "Git is a distributed version control system.");
responses.put("github", "GitHub is a platform for hosting Git repositories.");
responses.put("api", "API stands for Application Programming Interface.");
responses.put("oop", "OOP stands for Object-Oriented Programming.");
responses.put("ai", "Artificial Intelligence enables machines to perform tasks that normally require human intelligence.");
responses.put("machine learning", "Machine Learning is a branch of AI where systems learn from data.");
responses.put("deep learning", "Deep Learning uses neural networks with many layers.");
responses.put("chatgpt", "ChatGPT is an AI assistant developed by OpenAI.");

responses.put("cpu", "CPU stands for Central Processing Unit.");
responses.put("gpu", "GPU stands for Graphics Processing Unit.");
responses.put("ram", "RAM temporarily stores data for running programs.");
responses.put("ssd", "SSD is faster than a traditional HDD.");
responses.put("hdd", "Hard Disk Drives store data using spinning magnetic disks.");
responses.put("operating system", "An operating system manages computer hardware and software.");
responses.put("windows", "Windows is an operating system developed by Microsoft.");
responses.put("linux", "Linux is an open-source operating system.");
responses.put("macos", "macOS is Apple's desktop operating system.");

responses.put("who discovered gravity", "Isaac Newton is famous for formulating the law of gravity.");
responses.put("einstein", "Albert Einstein developed the theory of relativity.");
responses.put("newton", "Isaac Newton made major contributions to physics and mathematics.");
responses.put("tesla scientist", "Nikola Tesla was a pioneer in electrical engineering.");
responses.put("marie curie", "Marie Curie won Nobel Prizes in both Physics and Chemistry.");
responses.put("cv raman", "C. V. Raman discovered the Raman Effect.");
responses.put("apj abdul kalam", "Dr. A.P.J. Abdul Kalam was an aerospace scientist and the 11th President of India.");

responses.put("photosynthesis", "Photosynthesis converts sunlight into chemical energy in plants.");
responses.put("water formula", "The chemical formula of water is H2O.");
responses.put("oxygen", "Oxygen makes up about 21% of Earth's atmosphere.");
responses.put("hydrogen", "Hydrogen is the most abundant element in the universe.");
responses.put("dna", "DNA carries genetic information in living organisms.");
responses.put("cell", "The cell is the basic unit of life.");
responses.put("human heart", "The human heart has four chambers.");
responses.put("human body", "The adult human body has 206 bones.");
responses.put("largest organ", "The skin is the largest organ of the human body.");
responses.put("largest internal organ", "The liver is the largest internal organ.");
responses.put("brain", "The human brain contains roughly 86 billion neurons.");

responses.put("who wrote ramayana", "The Ramayana is traditionally attributed to Maharishi Valmiki.");
responses.put("who wrote mahabharata", "The Mahabharata is traditionally attributed to Maharishi Vyasa.");
responses.put("gita", "The Bhagavad Gita is a dialogue between Lord Krishna and Arjuna.");
responses.put("veda", "There are four Vedas: Rigveda, Yajurveda, Samaveda, and Atharvaveda.");

responses.put("independence day", "India celebrates Independence Day on 15 August.");
responses.put("republic day", "India celebrates Republic Day on 26 January.");
responses.put("constitution of india", "The Constitution of India came into effect on 26 January 1950.");
responses.put("national animal", "The Bengal Tiger is India's national animal.");
responses.put("national bird", "The Indian Peacock is the national bird of India.");
responses.put("national flower", "The Lotus is India's national flower.");
responses.put("national fruit", "The Mango is India's national fruit.");
responses.put("national anthem", "Jana Gana Mana is the national anthem of India.");
responses.put("national song", "Vande Mataram is India's national song.");

responses.put("who am i", "You are an awesome human with unlimited potential.");
responses.put("meaning of success", "Success means achieving goals while continuing to learn and grow.");
responses.put("hard work", "Hard work combined with smart work leads to better results.");
responses.put("discipline", "Discipline is doing what needs to be done even when you don't feel like it.");
responses.put("time management", "Prioritize important tasks and avoid procrastination.");
responses.put("communication", "Good communication involves listening as much as speaking.");
responses.put("leadership", "A good leader inspires and empowers others.");
responses.put("teamwork", "Teamwork combines different strengths to achieve common goals.");
responses.put("critical thinking", "Critical thinking means evaluating information logically before reaching conclusions.");
responses.put("problem solving", "Break complex problems into smaller manageable parts.");
responses.put("what is internet", "The Internet is a global network connecting millions of computers.");
responses.put("who invented internet", "The Internet evolved through the work of many researchers. Vint Cerf and Bob Kahn are often called the fathers of the Internet.");
responses.put("what is wifi", "Wi-Fi is a wireless technology used to connect devices to the Internet.");
responses.put("what is bluetooth", "Bluetooth allows short-range wireless communication between devices.");
responses.put("what is cloud computing", "Cloud computing provides computing services over the Internet.");
responses.put("what is blockchain", "Blockchain is a distributed digital ledger technology.");
responses.put("what is cryptocurrency", "Cryptocurrency is digital money secured by cryptography.");
responses.put("bitcoin", "Bitcoin was introduced in 2009 by the pseudonymous Satoshi Nakamoto.");
responses.put("ethereum", "Ethereum is a blockchain platform supporting smart contracts.");
responses.put("what is cyber security", "Cybersecurity protects systems, networks, and data from cyber attacks.");
responses.put("what is hacking", "Hacking means finding vulnerabilities in computer systems. Ethical hacking is used to improve security.");
responses.put("what is phishing", "Phishing is an attempt to steal sensitive information using fake emails or websites.");
responses.put("what is malware", "Malware is software designed to damage or gain unauthorized access to systems.");
responses.put("what is virus", "A computer virus is malicious software that spreads by infecting files.");
responses.put("what is vpn", "A VPN encrypts your Internet connection for privacy.");
responses.put("what is firewall", "A firewall filters incoming and outgoing network traffic.");

responses.put("what is machine", "A machine is a device that helps perform work.");
responses.put("what is electricity", "Electricity is the flow of electric charge.");
responses.put("what is voltage", "Voltage is the electrical potential difference.");
responses.put("what is current", "Electric current is the flow of electric charge.");
responses.put("what is resistance", "Resistance opposes the flow of electric current.");
responses.put("ohm law", "Ohm's Law states V = I × R.");
responses.put("what is magnet", "A magnet produces a magnetic field that attracts certain materials.");
responses.put("renewable energy", "Solar, wind, hydro, and geothermal are renewable energy sources.");
responses.put("solar energy", "Solar energy is produced from sunlight.");
responses.put("wind energy", "Wind turbines convert wind into electricity.");
responses.put("hydroelectricity", "Hydroelectric power is generated using flowing water.");

responses.put("what is democracy", "Democracy is a system where people elect their representatives.");
responses.put("what is constitution", "A constitution is the supreme legal framework of a country.");
responses.put("what is parliament", "Parliament is the legislative body responsible for making laws.");
responses.put("what is judiciary", "The judiciary interprets laws and delivers justice.");
responses.put("what is executive", "The executive implements and enforces laws.");
responses.put("what is legislature", "The legislature debates and passes laws.");
responses.put("what is election commission", "The Election Commission conducts free and fair elections in India.");
responses.put("what is lok sabha", "Lok Sabha is the lower house of India's Parliament.");
responses.put("what is rajya sabha", "Rajya Sabha is the upper house of India's Parliament.");
responses.put("fundamental duties", "Fundamental Duties encourage citizens to contribute responsibly to the nation.");

responses.put("what is economy", "An economy is the system of production, distribution, and consumption of goods and services.");
responses.put("what is inflation", "Inflation is the rise in the general price level over time.");
responses.put("what is gdp", "GDP measures the total value of goods and services produced in a country.");
responses.put("what is taxation", "Tax is money collected by governments to fund public services.");
responses.put("income tax", "Income tax is charged on earnings according to applicable tax laws.");
responses.put("gst meaning", "GST stands for Goods and Services Tax.");
responses.put("what is bank", "A bank provides financial services like deposits and loans.");
responses.put("what is atm", "ATM stands for Automated Teller Machine.");
responses.put("what is upi", "UPI enables instant digital money transfers between bank accounts.");
responses.put("what is digital payment", "Digital payments allow electronic transfer of money.");

responses.put("what is photosynthesis", "Plants use sunlight, carbon dioxide, and water to produce food.");
responses.put("what is respiration", "Respiration releases energy from food.");
responses.put("what is osmosis", "Osmosis is the movement of water through a selectively permeable membrane.");
responses.put("what is diffusion", "Diffusion is the movement of particles from higher to lower concentration.");
responses.put("blood groups", "The major blood groups are A, B, AB, and O.");
responses.put("largest bone", "The femur is the longest and strongest bone.");
responses.put("smallest bone", "The stapes is the smallest bone in the human body.");
responses.put("largest cell", "The ostrich egg is the largest single cell.");
responses.put("smallest cell", "Mycoplasma is among the smallest free-living cells.");
responses.put("vitamin c", "Vitamin C supports the immune system and helps in wound healing.");

responses.put("periodic table", "The periodic table organizes chemical elements by atomic number.");
responses.put("atomic number", "Atomic number equals the number of protons in an atom.");
responses.put("atomic mass", "Atomic mass is the average mass of an element's atoms.");
responses.put("acid", "Acids generally release hydrogen ions in water.");
responses.put("base", "Bases generally release hydroxide ions in water.");
responses.put("ph scale", "The pH scale ranges from 0 to 14.");
responses.put("neutral substance", "Pure water has a pH of about 7.");
responses.put("chemical reaction", "A chemical reaction transforms substances into new substances.");
responses.put("oxidation", "Oxidation involves loss of electrons.");
responses.put("reduction", "Reduction involves gain of electrons.");

responses.put("newton first law", "An object remains at rest or in uniform motion unless acted upon by a force.");
responses.put("newton second law", "Force equals mass multiplied by acceleration.");
responses.put("newton third law", "Every action has an equal and opposite reaction.");
responses.put("gravity", "Gravity attracts objects toward one another.");
responses.put("speed", "Speed is distance divided by time.");
responses.put("velocity", "Velocity is speed with direction.");
responses.put("acceleration", "Acceleration is the rate of change of velocity.");
responses.put("work", "Work equals force multiplied by displacement.");
responses.put("power", "Power is the rate of doing work.");
responses.put("energy", "Energy is the capacity to do work.");

responses.put("who is mahatma gandhi", "Mahatma Gandhi led India's freedom movement through non-violent resistance.");
responses.put("who is bhagat singh", "Bhagat Singh was a revolutionary freedom fighter.");
responses.put("who is subhash chandra bose", "Subhash Chandra Bose founded the Indian National Army.");
responses.put("who is jawaharlal nehru", "Jawaharlal Nehru was independent India's first Prime Minister.");
responses.put("who is sardar patel", "Sardar Vallabhbhai Patel played a key role in unifying India.");
responses.put("who is rani lakshmi bai", "Rani Lakshmi Bai was a prominent leader in the Revolt of 1857.");
responses.put("taj mahal", "The Taj Mahal is a UNESCO World Heritage Site in Agra.");
responses.put("qutub minar", "Qutub Minar is one of the tallest brick minarets in the world.");
responses.put("red fort", "The Red Fort is a historic fort in Delhi.");
responses.put("gateway of india", "The Gateway of India is a famous monument in Mumbai.");

responses.put("what is startup", "A startup is a young company focused on developing innovative products or services.");
responses.put("entrepreneur", "An entrepreneur starts and manages a business.");
responses.put("leadership skills", "Leadership involves communication, decision-making, and teamwork.");
responses.put("public speaking", "Practice and preparation improve public speaking.");
responses.put("time value", "Time is one of your most valuable resources.");
responses.put("discipline vs motivation", "Motivation starts the journey, discipline keeps you going.");
responses.put("habit", "Small daily habits create long-term success.");
responses.put("focus", "Focus on one important task at a time.");
responses.put("productivity", "Plan your work and minimize distractions.");
responses.put("success", "Success is built through consistent effort over time.");
responses.put("who discovered america", "Christopher Columbus reached the Americas in 1492, although indigenous peoples had lived there for thousands of years.");
responses.put("who discovered india", "India was never 'discovered'; it has one of the world's oldest civilizations.");
responses.put("oldest civilization", "Mesopotamia is often considered one of the earliest known civilizations.");
responses.put("oldest university", "The University of al-Qarawiyyin in Morocco is often recognized as the world's oldest continuously operating university.");
responses.put("largest democracy", "India is the world's largest democracy.");
responses.put("united nations", "The United Nations was established in 1945 to promote international peace and cooperation.");
responses.put("who founded united nations", "The United Nations was founded by 51 member states after World War II.");
responses.put("world war 1", "World War I lasted from 1914 to 1918.");
responses.put("world war 2", "World War II lasted from 1939 to 1945.");
responses.put("cold war", "The Cold War was a period of geopolitical tension mainly between the United States and the Soviet Union.");
responses.put("nato", "NATO is a military alliance founded in 1949.");
responses.put("who is mahatma gandhi", "Mahatma Gandhi led India's independence movement using non-violent resistance.");
responses.put("who is dr b r ambedkar", "Dr. B. R. Ambedkar was the chief architect of the Indian Constitution.");
responses.put("constitution of india", "The Constitution of India came into force on 26 January 1950.");
responses.put("fundamental rights", "The Indian Constitution guarantees six categories of Fundamental Rights.");
responses.put("fundamental duties", "Fundamental Duties encourage citizens to uphold the values of the Constitution.");
responses.put("directive principles", "Directive Principles guide the government in policymaking.");

responses.put("who is isaac newton", "Isaac Newton formulated the laws of motion and universal gravitation.");
responses.put("who is albert einstein", "Albert Einstein developed the theory of relativity.");
responses.put("theory of relativity", "Einstein's theory explains gravity as the curvature of spacetime.");
responses.put("e mc2", "E = mc² expresses the equivalence of mass and energy.");
responses.put("who is nikola tesla", "Nikola Tesla made major contributions to alternating current (AC) electricity.");
responses.put("who is galileo", "Galileo Galilei improved the telescope and supported the heliocentric model.");
responses.put("who is charles darwin", "Charles Darwin proposed the theory of evolution by natural selection.");
responses.put("who is stephen hawking", "Stephen Hawking was a theoretical physicist known for his work on black holes.");
responses.put("black hole", "A black hole is a region where gravity is so strong that even light cannot escape.");
responses.put("what is gravity", "Gravity is the force of attraction between masses.");

responses.put("what is ai", "Artificial Intelligence enables computers to perform tasks that usually require human intelligence.");
responses.put("what is generative ai", "Generative AI creates new content such as text, images, music, or code from learned patterns.");
responses.put("what is llm", "An LLM, or Large Language Model, is trained on vast amounts of text to understand and generate language.");
responses.put("what is chatgpt", "ChatGPT is an AI assistant developed by OpenAI.");
responses.put("what is neural network", "A neural network is a machine learning model inspired by the human brain.");
responses.put("what is data science", "Data science combines statistics, programming, and domain knowledge to analyze data.");
responses.put("what is big data", "Big Data refers to datasets that are too large or complex for traditional processing.");
responses.put("what is internet of things", "The Internet of Things connects physical devices to the internet.");
responses.put("what is quantum computing", "Quantum computing uses qubits and quantum mechanics to solve certain problems more efficiently.");
responses.put("what is cloud", "Cloud computing provides computing resources over the internet.");

responses.put("http", "HTTP stands for HyperText Transfer Protocol.");
responses.put("https", "HTTPS is the secure version of HTTP using encryption.");
responses.put("ip address", "An IP address uniquely identifies a device on a network.");
responses.put("dns", "DNS translates domain names into IP addresses.");
responses.put("domain name", "A domain name is the human-readable address of a website.");
responses.put("browser", "A web browser allows users to access websites.");
responses.put("google", "Google is one of the world's largest technology companies.");
responses.put("youtube", "YouTube is a popular online video-sharing platform.");
responses.put("email", "Email is an electronic messaging system.");
responses.put("password", "Use strong, unique passwords and enable two-factor authentication whenever possible.");

responses.put("what is recursion", "Recursion is a technique where a function calls itself.");
responses.put("what is array", "An array stores multiple values of the same type.");
responses.put("what is linked list", "A linked list stores elements as nodes connected by pointers.");
responses.put("what is stack", "A stack follows the Last In, First Out principle.");
responses.put("what is queue", "A queue follows the First In, First Out principle.");
responses.put("what is tree", "A tree is a hierarchical data structure.");
responses.put("what is graph", "A graph consists of vertices connected by edges.");
responses.put("what is binary search", "Binary search finds an element in a sorted array in O(log n) time.");
responses.put("time complexity", "Time complexity estimates how the running time grows with input size.");
responses.put("space complexity", "Space complexity measures the amount of memory used by an algorithm.");

responses.put("html", "HTML structures web pages.");
responses.put("css", "CSS styles web pages.");
responses.put("javascript", "JavaScript adds interactivity to websites.");
responses.put("react", "React is a JavaScript library for building user interfaces.");
responses.put("node js", "Node.js allows JavaScript to run on the server.");
responses.put("spring boot", "Spring Boot simplifies Java backend development.");
responses.put("rest api", "A REST API allows applications to communicate over HTTP.");
responses.put("json", "JSON is a lightweight format for storing and exchanging data.");
responses.put("xml", "XML is a markup language used to store and transport data.");
responses.put("database", "A database stores and organizes data efficiently.");

responses.put("what is happiness", "Happiness often comes from meaningful relationships, purpose, and good health.");
responses.put("what is success", "Success means achieving goals that are important to you while continuing to grow.");
responses.put("what is discipline", "Discipline is consistently doing what needs to be done, even when it's difficult.");
responses.put("what is confidence", "Confidence grows through preparation, practice, and experience.");
responses.put("how to focus", "Remove distractions, work in short focused sessions, and take regular breaks.");
responses.put("how to learn fast", "Practice actively, revise regularly, and teach others what you learn.");
responses.put("how to improve memory", "Sleep well, revise consistently, and use active recall.");
responses.put("how to reduce stress", "Exercise, sleep well, talk to trusted people, and manage your time.");
responses.put("how to become successful", "Set clear goals, stay consistent, and keep learning.");
responses.put("how to become rich", "Build valuable skills, save wisely, invest carefully, and think long term.");

responses.put("what is cricket", "Cricket is a bat-and-ball sport played between two teams of eleven players.");
responses.put("what is football", "Football is one of the most popular sports in the world.");
responses.put("olympics", "The Olympic Games are the world's largest international sporting event.");
responses.put("fifa", "FIFA organizes international football competitions, including the World Cup.");
responses.put("icc", "The ICC governs international cricket.");

responses.put("earth atmosphere", "Earth's atmosphere is mainly nitrogen and oxygen.");
responses.put("ozone layer", "The ozone layer helps protect Earth from harmful ultraviolet radiation.");
responses.put("climate change", "Climate change refers to long-term shifts in temperatures and weather patterns.");
responses.put("global warming", "Global warming is the long-term increase in Earth's average surface temperature.");
responses.put("recycling", "Recycling helps reduce waste and conserve resources.");
responses.put("pollution", "Pollution can affect air, water, soil, and human health.");
responses.put("biodiversity", "Biodiversity is the variety of life on Earth.");
responses.put("ecosystem", "An ecosystem is a community of living organisms interacting with their environment.");
responses.put("renewable resources", "Renewable resources can naturally replenish over time.");
responses.put("non renewable resources", "Coal, petroleum, and natural gas are examples of non-renewable resources.");
    }

    public void actionPerformed(ActionEvent e) {

        String user = inputField.getText().trim();

        if(user.isEmpty())
            return;

        appendColored("YOU", NEON_PINK, true);
        appendColored(" > " + user + "\n", Color.WHITE, false);

        String botReply = getResponse(user);

        appendColored("BOT", NEON_GREEN, true);
        appendColored(" > " + botReply + "\n\n", NEON_CYAN, false);

        chatArea.setCaretPosition(chatArea.getDocument().getLength());

        inputField.setText("");
    }


    private void appendColored(String text, Color color, boolean bold) {
        StyledDocument doc = chatArea.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setForeground(attrs, color);
        StyleConstants.setBold(attrs, bold);
        StyleConstants.setFontFamily(attrs, "Consolas");
        StyleConstants.setFontSize(attrs, 14);
        try {
            doc.insertString(doc.getLength(), text, attrs);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private static class NeonScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = NEON_PURPLE;
            this.trackColor = BG_DARK;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }

        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            return button;
        }
    }

    private boolean matchesKeyword(String message, String keyword) {
        if (keyword.matches(".*[a-zA-Z0-9].*")) {
            String pattern = "\\b" + Pattern.quote(keyword) + "\\b";
            return Pattern.compile(pattern).matcher(message).find();
        } else {
            return message.contains(keyword);
        }
    }

    private boolean matchesAny(String message, String... keywords) {
        for (String k : keywords) {
            if (matchesKeyword(message, k)) return true;
        }
        return false;
    }

    public String getResponse(String message) {
        message = message.toLowerCase().trim();

        if(message.startsWith("my name is ")) {
            userName = message.substring(11).trim();
            return "Nice to meet you, " + userName + " 😊";
        }
        if(matchesKeyword(message, "what is my name"))
            return userName.isEmpty() ? "You haven't told me your name yet." : "Your name is " + userName + " 😄";

        if(matchesAny(message, "hello", "hi", "hey", "yo"))
            return helloReplies[random.nextInt(helloReplies.length)];
        if(matchesAny(message, "joke", "funny"))
            return jokes[random.nextInt(jokes.length)];
        if(matchesAny(message, "bye", "exit", "see you"))
            return byeReplies[random.nextInt(byeReplies.length)];
        if(matchesAny(message, "thank", "thanks", "thank you"))
            return thanksReplies[random.nextInt(thanksReplies.length)];
        if(matchesKeyword(message, "motivate"))
            return motivationReplies[random.nextInt(motivationReplies.length)];
        if(matchesAny(message, "sad", "upset"))
            return sadReplies[random.nextInt(sadReplies.length)];
        if(matchesKeyword(message, "happy"))
            return happyReplies[random.nextInt(happyReplies.length)];
        if(matchesAny(message, "bro", "bruh", "sus"))
            return roastReplies[random.nextInt(roastReplies.length)];
        if(matchesAny(message, "code", "coding", "program", "programming"))
            return codingReplies[random.nextInt(codingReplies.length)];

       
        String bestKey = null;
        int bestScore = -1;

        for (String key : responses.keySet()) {
            if (matchesKeyword(message, key)) {
                int score = key.split("\\s+").length;
                if (score > bestScore) {
                    bestScore = score;
                    bestKey = key;
                }
            }
        }

        if (bestKey != null) {
            return responses.get(bestKey);
        }

        return unknownReplies[random.nextInt(unknownReplies.length)];
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            CodeAlpha_chatBot bot = new CodeAlpha_chatBot();
            bot.setVisible(true);

        });

    }
}
package schoolTour.personalProject;

import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel currentPanel;
    public MainFrame() {
        setTitle("대학생 키우기");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        switchToMap("campus");
    }

    public void switchToMap(String mapName) {
        if(currentPanel != null) {
            remove(currentPanel);
        }

        // 새로운 main.GamePanel 생성
        currentPanel = new GamePanel(this, mapName);
        add(currentPanel); // 새 패널 추가

        revalidate(); // 레이아웃 다시 계산
        repaint(); // 화면 다시 그리기
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

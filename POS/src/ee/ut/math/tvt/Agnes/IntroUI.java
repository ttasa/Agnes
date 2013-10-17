package ee.ut.math.tvt.Agnes;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroUI {
	
	JFrame f = new JFrame("Agnes POS");
	JPanel p = new JPanel();
	JPanel pic = new JPanel();
	
	String teamName = "";
	String teamLeader = "";
	String leaderEmail = "";
	String teamMembers = "";
	String version = "";
	String logo = "";

	JLabel img = new JLabel();
	
	Properties prop = new Properties();
	
	public IntroUI(){
		
		try {
			prop.load(this.getClass().getResourceAsStream("/application.properties"));
			teamName = prop.getProperty("team.name");
			teamLeader = prop.getProperty("team.leader");
			leaderEmail = prop.getProperty("team.leader.email");
			teamMembers = prop.getProperty("team.members");
			logo = prop.getProperty("team.logo");
			
			prop.load(this.getClass().getResourceAsStream("/version.properties"));
			version = prop.getProperty("build.number");
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			img = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/"+logo))));
		} catch (IOException e) {
		}
		
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setLayout(new BorderLayout());
		f.setResizable(false);
		
		p.setLayout(new BoxLayout(p, 1));
		
		p.add(new JLabel("Team name: " + teamName));
		p.add(Box.createVerticalStrut(10));
		p.add(new JLabel("Team leader: " + teamLeader));
		p.add(Box.createVerticalStrut(10));
		p.add(new JLabel("Team leader email: " + leaderEmail));
		p.add(Box.createVerticalStrut(10));
		p.add(new JLabel("Team members: " + teamMembers));
		p.add(Box.createVerticalStrut(10));
		p.add(new JLabel("Version: " + version));
		
		pic.add((img));
		
		f.add(p, BorderLayout.WEST);
		f.add(pic, BorderLayout.EAST);
		f.pack();
	}
}

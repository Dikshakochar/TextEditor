import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// java swing class for creating frame
public class accioeditor implements ActionListener {
    JFrame frame;
    // create the frame
    JMenuBar menubar;
    JMenu file;
    JMenu edit;
    JMenuItem newFile,openFile,saveFile;
    ///for file
    JMenuItem cut,copy,paste,selectAll,close;
    //creating text area
    JTextArea area;

    // menu bar containing the menu
    accioeditor()
    {
       frame=new JFrame();
       //text area
        area=new JTextArea();
        frame.add(area);
       menubar=new JMenuBar();
       file=new JMenu("File");
       edit=new JMenu("Edit");
       //for file menu item
       newFile=new JMenuItem("New");
       openFile=new JMenuItem("Open");
       saveFile=new JMenuItem("Save");
       //add action listener to menuitems
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
       file.add(newFile);
       file.add(openFile);
       file.add(saveFile);
       //for edit menu
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close Window");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
       //add menu to menubar
       menubar.add(file);
       menubar.add(edit);
       frame.setJMenuBar(menubar);// setter for the menubar
       //add menubar to frame
       frame.setBounds(100,100,800,500);
       frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
     if(actionEvent.getSource()==cut)
     {/// perform action according to cut event
         area.cut();
     }
     if(actionEvent.getSource()==copy)
     {
         area.copy();
     }
     if(actionEvent.getSource()==selectAll)
     {
         area.selectAll();
     }
     if(actionEvent.getSource()==paste)
     {
         area.paste();
     }
     if(actionEvent.getSource()==close)
     {
         System.exit(0);
     }
    if(actionEvent.getSource()==newFile)
    {
        //create new window
        accioeditor neweditor=new accioeditor();
    }
        if (actionEvent.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();

                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    String intermediate = "", output = "";
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output = output + intermediate + "\n";
                    }
                    area.setText(output);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION)
            {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                    area.write(outfile);
                    outfile.close();

                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
       accioeditor editor=new accioeditor();
    }
}
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GUIForm extends JDialog {
    private int realColCnt;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton добавитьButton;
    private JButton удалитьButton;
    private JButton вычислитьButton;
    private JTable table1;
    private double[] dataT = new double[4];
    int num =1;
    int rowNamber;
    Object[][] data = new Object[10][4];
    List<double[]> rowList = new ArrayList<double[]>();
    public GUIForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Лаба 1");
        
        createTable();
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        добавитьButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                добавитьButton();
            }
        });
        удалитьButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { удалитьButton();
            }
        });


        вычислитьButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                вычислитьButton();
            }
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }



    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    private void добавитьButton() {
        dataT[0] =Double.valueOf(textField1.getText());
        dataT[1] =Double.valueOf(textField3.getText());
        dataT[2] =Double.valueOf(textField2.getText());
        dataT[3] =Double.valueOf("0");
        createTable();
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
    }
    private void удалитьButton() {
        rowNamber=table1.getSelectedRow();
        data[rowNamber][0]="";//1
        data[rowNamber][1]="";//2
        data[rowNamber][2]="";//3
        data[rowNamber][3]="";//4

        table1.setModel(new DefaultTableModel(data, new String[]{"Верхняя граница интегрирования", "Нижняя граница интегрирования", "Шаг интегрирования", "Результат",}));


    }
    private void вычислитьButton() {

        dataT[3]= Trap(dataT[0],dataT[1],dataT[2]);
        createTable();
        num++;
    }
    public static double InFunction(double x) //Подынтегральная функция
    {
        return 1/(Math.log(x));
    }


    public double Trap(double a,double b, double h){
        double result=0;
        int n = (int)((a-b)/h);
        result += (InFunction(a)+InFunction(b))/2;
        for(int i = 1; i < n; i++) {
            result += InFunction(b + h * i);
        }
        return h*result;
    }

    public void createTable(){
        int i=100;

        data[num-1][0]=dataT[0];//1
        data[num-1][1]=dataT[1];//2
        data[num-1][2]=dataT[2];//3
        data[num-1][3]=dataT[3];//4

        table1.setModel(new DefaultTableModel(data, new String[]{"Верхняя граница интегрирования", "Нижняя граница интегрирования", "Шаг интегрирования", "Результат",}));
    }


    public static void main(String[] args) {
        GUIForm dialog = new GUIForm();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setName("laba1");

        System.exit(0);
    }


}


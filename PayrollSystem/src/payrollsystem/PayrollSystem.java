package payrollsystem;                                                              //package name

//import java packages
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
public class PayrollSystem extends JFrame{                                          //class name with extends to JFrame
    JPanel p,p2,p3;                                                                 //declare JPanel
    JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9,lb10,lb11,lb12;                      //declare JLabel
    JTextField txtEID,txtIC,txtName,txtSalary,txtGS,txtNI;                          //declare JTextField
    JComboBox Clark;                                                                //declare JComboBox
    JCheckBox nd,ph;                                                                //declare JCheckBox
    JRadioButton car,motor;                                                         //declare JRadioButton
    JButton calc,save,edit,clr,search;                                              //declare JButton
    String position[] =                                             
    {"--Select--","Executive","Manager","Operation and Production"};                //array for position
    String driver = "com.mysql.cj.jdbc.Driver";                                     //driver database
    String url ="jdbc:mysql://localhost:3306?zeroDateTimeBehavior=CONVERT_TO_NULL"; //url database
    String user = "root";                                                           //user database
    String pass = "";                                                               //password database
    String Eid,Ic,Nama,Pos,BS,GS,NI,OverT,TA,OTType,Trs;                            //declare variables
    int Basic_Salary,ndHours,phHours;                                               //declare variables
    float OT;                                                                       //declare variables
    double transport_allowance,gross_salary,net_income;                             //declare variables
PayrollSystem(){                                                                    //method CaseStudy
    
     setBounds(10,10,700,1000);                                                     //set size for JFrame
     setLayout(null);                                                               //set layout for JFrame
     setVisible(true);                                                              //set visibility for JFrame
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                //close JFrame if click exit
     setTitle("Automated Payroll System");                                          //set title for JFrame
     
     
     try{
            Class.forName(driver);                                                  //Load the JDBC driver
            Connection kon = DriverManager.getConnection(url, user, pass);          //Establish a database connection
            Statement stm = kon.createStatement();                                  //Create a Statement object
            String createDBQuery = "CREATE DATABASE IF NOT EXISTS payroll";         //sql statement to create database
            stm.executeUpdate(createDBQuery);                                       //execute sql statement

            String useDBQuery = "USE payroll";                                      // Switch to the Fira database
            stm.execute(useDBQuery);                                                //execute sql statement
            stm.execute("use payroll");
        
            String createTableQuery = "CREATE TABLE IF NOT EXISTS employee ("       // Create the employee table
                    + "Employee_ID VARCHAR(100), "
                    + "IC VARCHAR(20), "
                    + "Name VARCHAR(200), "
                    + "Position VARCHAR(200), "
                    + "Basic_Salary VARCHAR(200), "
                    + "Overtime_Rate VARCHAR(200), "
                    + "Overtime_Type VARCHAR(200), "
                    + "Transport VARCHAR(200), "
                    + "Transport_Allowance VARCHAR(200), "
                    + "Gross_Salary VARCHAR(200), "  
                    + "Net_Income VARCHAr(200))";
        stm.executeUpdate(createTableQuery);                                        //execute sql statement
        }
        catch(ClassNotFoundException | SQLException e){                             //if failed to create database and table
            JOptionPane.showMessageDialog(null,"create database not success");      //popup message
        }
     
     
     lb1 = new JLabel("AUTOMATED PAYROLL SYSTEM");                                  //create JLabel
     lb1.setBounds(90,20,400,100);                                                  //set width,height,location
     lb1.setForeground(Color.RED);                                                  //set font color
     lb1.setFont(new Font("Arial", Font.BOLD,20));                                  //set font style
     add(lb1);                                                                      //add to JFrame
     
     p = new JPanel();
     p.setBounds(90,100,500,200);                                                   //set width,height,location
     p.setLayout(null);                                                             //set layout for p
     p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
        (Color.blue),"Employee Information"));                                      //set Border Title and color
     add(p);                                                                        //add to JFrame
     
     lb2 = new JLabel("Employee ID");                                               //create JLabel
     lb2.setBounds(100,40,70,30);                                                   //set width,height,location
     p.add(lb2);                                                                    //add to p
     
     txtEID = new JTextField();                                                     //create JTextField
     txtEID.setBounds(180,46,200,20);                                               //set width,height,location
     p.add(txtEID);                                                                 //add to p
     
     lb3 = new JLabel("IC");                                                        //create JLabel
     lb3.setBounds(100,70,70,30);                                                   //set width,height,location
     p.add(lb3);                                                                    //add to p
     
     txtIC = new JTextField();                                                      //create JTextField
     txtIC.setBounds(180,76,200,20);                                                //set width,height,location
     p.add(txtIC);                                                                  //add to p
     
     lb4 = new JLabel("Name");                                                      //create JLabel
     lb4.setBounds(100,100,70,30);                                                  //set width,height,location
     p.add(lb4);                                                                    //add to p
     
     txtName = new JTextField();                                                    //create JTextField
     txtName.setBounds(180,106,200,20);                                             //set width,height,location
     p.add(txtName);                                                                //add to p
     
     lb5 = new JLabel("Position");                                                  //create JLabel
     lb5.setBounds(100,130,70,30);                                                  //set width,height,location
     p.add(lb5);                                                                    //add to p
     
     Clark = new JComboBox(position);                                               //create JComboBox
     Clark.setBounds(180,130,200,30);                                               //set width,height,location
     p.add(Clark);                                                                  //add to p
     
     Clark.addActionListener((ActionEvent e) ->{                                    //add Action Listener to Clark
         switch (Clark.getSelectedIndex()) {                                        //switch statement
             case 1 -> Basic_Salary = 2800;                                         //set value to Basic_Salary
             case 2 -> Basic_Salary = 4500;                                         //set value to Basic_Salary
             case 3 -> Basic_Salary = 1800;                                         //set value to Basic_Salary
             default -> {
             }
         }
         txtSalary.setText(String.valueOf(Basic_Salary));                           //setText to txtSalary with Basic_Salary value
     });
     
     p2 = new JPanel();                                                             //create JPanel
     p2.setBounds(90,330,500,220);                                                  //set width,height,location
     p2.setLayout(null);                                                            //set layout for p2
     p2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
        (Color.blue),"Salary Information"));                                        //set Border Title and color
     add(p2);                                                                       //add to JFrame
     
     lb6 = new JLabel("Basic Salary(RM):");                                         //create JLabel
     lb6.setBounds(100,40,110,30);                                                  //set width,height,location
     p2.add(lb6);                                                                   //add to p2
     
     txtSalary = new JTextField();                                                  //create JTextField
     txtSalary.setBounds(210,46,120,20);                                            //set width,height,location
     txtSalary.setEnabled(false);                                                   //Disabling txtSalary.
     p2.add(txtSalary);                                                             //add to p2
     
     lb7 = new JLabel("Overtime Rate");                                             //create JLabel
     lb7.setBounds(100,70,100,30);                                                  //set width,height,location
     p2.add(lb7);                                                                   //add to p2
     
     nd = new JCheckBox("Normal Day");                                              //create JCheckBox
     nd.setBounds(220,73,110,30);                                                   //set width,height,location
     p2.add(nd);                                                                    //add to p2
     
    
     lb8 = new JLabel("RM 15.00/hour");                                             //create JLabel
     lb8.setBounds(340,75,100,20);                                                  //set width,height,location
     lb8.setBorder(BorderFactory.createLineBorder(Color.BLUE));                     //set Border Title and color
     p2.add(lb8);                                                                   //add to p2
     
     ph = new JCheckBox("Public Holiday");                                          //create JCheckBox
     ph.setBounds(220,103,110,30);                                                  //set width,height,location
     p2.add(ph);                                                                    //add to p2
     
     
     ItemListener itemListener = (ItemEvent e) -> {
         if(nd.isSelected()){
             String f = JOptionPane.showInputDialog("How Many Hours");
             ndHours = Integer.parseInt(f);
             OT = ndHours * 15;
             nd.setSelected(true);                                                  //Set nd to selected
             ph.setSelected(false);                                                 //Set nd to selected
             
         }
         else if(ph.isSelected()){
             String g = JOptionPane.showInputDialog("How Many Hours");
             phHours = Integer.parseInt(g);
             OT = phHours * 20;
             ph.setSelected(true);                                                  //Set nd to selected
             nd.setSelected(false);                                                 //Set nd to selected
         }
     };
     
nd.addItemListener(itemListener);                                                   //add Action Listener to nd
ph.addItemListener(itemListener);                                                   //add Action Listener to ph
     
     lb9 = new JLabel("RM 20.00/hour");                                             //create JLabel
     lb9.setBounds(340,105,100,20);                                                 //set width,height,location
     lb9.setBorder(BorderFactory.createLineBorder(Color.BLUE));                     //set Border Title and color
     p2.add(lb9);                                                                   //add to p2
     
     lb10 = new JLabel("Transport Allowance");                                      //create JLabel
     lb10.setBounds(100,145,120,30);                                                //set width,height,location
     p2.add(lb10);                                                                  //add to p2
     
     ButtonGroup g = new ButtonGroup();                                             //create ButtonGroup
     
     car = new JRadioButton("Car");                                                 //create JRadioButton
     car.setBounds(220,145,70,30);                                                  //set width,height,location
     g.add(car);                                                                    //add to g
     p2.add(car);                                                                   //add to p2
     
     motor = new JRadioButton("Motorcycle");                                        //create JRadioButton
     motor.setBounds(220,175,120,30);                                               //set width,height,location
     g.add(motor);                                                                  //add to g
     p2.add(motor);                                                                 //add to p2
     
     
     
     p3 = new JPanel();                                                             //create JPanel
     p3.setBounds(90,570,500,120);                                                  //set width,height,location
     p3.setLayout(null);                                                            //set layout for p3
     p3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder
        (Color.blue),"Total Salary"));                                              //set Border Title and color
     add(p3);                                                                       //add to JFrame
     
     lb11 = new JLabel("Gross Salary (RM) : ");                                     //create JLabel
     lb11.setBounds(100,40,120,30);                                                 //set width,height,location
     p3.add(lb11);                                                                  //add to p3
     
     txtGS = new JTextField();                                                      //create JTextField
     txtGS.setBounds(240,46,200,20);                                                //set width,height,location
     txtGS.setEnabled(false);                                                       //Disabling txtGS.
     p3.add(txtGS);                                                                 //add to p3
     
     lb12 = new JLabel("NET INCOME (RM) : ");                                       //create JLabel
     lb12.setBounds(100,70,120,30);                                                 //set width,height,location
     lb12.setForeground(Color.BLUE);                                                //set font color
     p3.add(lb12);                                                                  //add to p3
     
     txtNI = new JTextField();                                                      //create JTextField
     txtNI.setBounds(240,76,200,20);                                                //set width,height,location
     txtNI.setEnabled(false);                                                       //Disabling txtNI.
     p3.add(txtNI);                                                                 //add to p3
     
     calc = new JButton("CALCULATE");                                               //create JButton
     calc.setBounds(50,710,110,30);                                                 //set width,height,location
     add(calc);                                                                     //add to JFrame
     
     save = new JButton("SAVE");                                                    //create JButton
     save.setBounds(170,710,110,30);                                                //set width,height,location
     add(save);                                                                     //add to JFrame
     
     edit = new JButton("UPDATE");                                                  //create JButton
     edit.setBounds(290,710,110,30);                                                //set width,height,location
     add(edit);                                                                     //add to JFrame
     
     clr = new JButton("CLEAR");                                                    //create JButton
     clr.setBounds(410,710,110,30);                                                 //set width,height,location
     add(clr);                                                                      //add to JFrame
     
     search = new JButton("SEARCH");                                                //create JButton
     search.setBounds(530,710,110,30);                                              //set width,height,location
     add(search);                                                                   //add to JFrame
     
     calc.addMouseListener(new MouseAdapter()                                       //add Mouse Listener to calc
     {
         @Override
         public void mouseEntered(MouseEvent x)                                     //add Mouse Entered
         {
             calc.setBackground(Color.gray);                                        //set Background color
             calc.setForeground(Color.white);                                       //set font color
         }
         @Override
         public void mouseExited(MouseEvent x)                                      //add Mouse Exited
         {
             calc.setBackground(UIManager.getColor("control"));                     //set Background color
             calc.setForeground(Color.black);                                       //set font color
         }
     
     });
        
    calc.addActionListener((ActionEvent e) -> {                                     //add Action Listener to calc
        if (Clark.getSelectedIndex() == 0){                                         //Checking Clark's selection index value
            JOptionPane.showMessageDialog
                        (null, "Please Choose Your Position");                      //popup message
        }
        
        if(car.isSelected()){                                                       //Checking if car is selected
            transport_allowance = Basic_Salary * 0.06;                              //formula to count transport_allowance
        }else if(motor.isSelected()){                                               //Checking if motor is selected
            transport_allowance = Basic_Salary * 0.03;                              //formula to count transport_allowance
        }
        
        gross_salary = Basic_Salary + OT + transport_allowance;                     //formula to count gross_salary
        net_income=gross_salary;                                                    //formula to count net_income
        txtGS.setText(String.valueOf(gross_salary));                                //set text to txtGS
        txtNI.setText(String.valueOf(net_income));                                  //set text to txtNI
     });
    
     save.addMouseListener(new MouseAdapter()                                       //add Mouse Listener to save
     {
         @Override
         public void mouseEntered(MouseEvent x)                                     //add Mouse Entered
         {
             save.setBackground(Color.gray);                                        //set Background color
             save.setForeground(Color.white);                                       //set font color
         }
         @Override
         public void mouseExited(MouseEvent x)                                      //add Mouse Exited
         {
             save.setBackground(UIManager.getColor("control"));                     //set Background color
             save.setForeground(Color.black);                                       //set font color
         }
     
     });
     
    save.addActionListener((ActionEvent e)->{                                       //add Action Listener to save
             if(car.isSelected()){                                                  //Checking if car is Selected
                 Trs="Car";                                                         //set Trs value
                 transport_allowance = Basic_Salary * 0.06;                         //formula to count transport_allowance
             }else if(motor.isSelected()){                                          //Checking if Moto is Selected
                 Trs="Motorcycle";                                                  //set Trs value
                 transport_allowance = Basic_Salary * 0.03;                         //formula to count transport_allowance
             }
             
            if(nd.isSelected()){                                                    //Checking if nd is Selected
                OTType = "Normal Day";                                              //set OTType value
            }
            else if(ph.isSelected()){                                               //Checking if pf
                OTType = "Public Holiday";                                          //set OTType value
            }
            
        gross_salary = Basic_Salary + OT + transport_allowance;                     //formula to count gross_salary
        net_income=gross_salary;                                                    //formula to count net_income
        txtGS.setText(String.valueOf(gross_salary));                                //set text to txtGS
        txtNI.setText(String.valueOf(net_income));                                  //set text to txtNI
            
         switch (Clark.getSelectedIndex()) {                                        //swith statement
             case 1 -> Pos=position[1];                                             //set pos value using calling value in position array
             case 2 -> Pos=position[2];                                             //set pos value using calling value in position array
             case 3 -> Pos=position[3];                                             //set pos value using calling value in position array
             default -> JOptionPane.showMessageDialog
                        (null, "Please Choose Your Position");                      //popup message
         }
            try{
                 Class.forName(driver);                                             //Load the JDBC driver
                 Connection kon = DriverManager.getConnection(url, user, pass);     //Establish a database connection
                 Statement stm = kon.createStatement();                             //Create a Statement object
                 
                 String useDBQuery = "USE payroll";                                 //Switch to the payroll database
                 stm.execute(useDBQuery);                                           //execute sql statement
                 
                 Eid=txtEID.getText();                                              //set Eid value
                 Ic=txtIC.getText();                                                //set Ic value
                 Nama=txtName.getText();                                            //set Nama value
                 BS=txtSalary.getText();                                            //set BS value
                 OverT = String.valueOf(OT);                                        //set OverT value
                 TA = String.valueOf(transport_allowance);                          //set TA value
                 GS=txtGS.getText();                                                //set GS value
                 NI=txtNI.getText();                                                //set NI value
                 
                 if(!Eid.equals("")&&!Ic.equals("")&&!Nama.equals("")&&             //if form not null
                         !BS.equals("")&&!OverT.equals("")&&!TA.equals("")&&
                         !GS.equals("")&&!NI.equals("")&&!Pos.equals("")){
                     
                     String selectQuery = 
                            "SELECT Employee_ID FROM employee WHERE Employee_ID = "
                             + "?";                                                 //Check if Employee_ID exists in employee table
                    PreparedStatement selectStatement = 
                            kon.prepareStatement(selectQuery);                      //Prepare SQL statement for execution
                    selectStatement.setString(1, Eid);
                    ResultSet resultSet = selectStatement.executeQuery();           //Execute the SQL query and retrieve the result set

                    if (resultSet.next()) {                                         //Check if there is a next row in the result set
                        JOptionPane.showMessageDialog
                        (null, "Your ID already submited.");                        //popup message
                    } else {
                        String sql = "INSERT INTO employee (Employee_ID, IC, "      //insert into table employee in payroll database
                                + "Name, "
                                + "Position, Basic_Salary, "
                                + "Overtime_Rate, Overtime_Type, "
                                + "Transport,Transport_Allowance, "
                                + "Gross_Salary, Net_Income) "
                                + "VALUES ('" + Eid + "', '" 
                                + Ic + "', '" + Nama + "', '"
                                + Pos + "', '" + BS + "', '" 
                                + OverT + "', '" + OTType + "', '"
                                + Trs + "', '" + TA + "', '"
                                + GS + "', '" + NI + "')";

                        stm.executeUpdate(sql);                                     //execute sql statement
                        JOptionPane.showMessageDialog(null, "Successful Registered");//popup message
                    }
                 }
                else{
                    JOptionPane.showMessageDialog(null,"Please fill all the data"); //popup message
                }
             }
             catch(HeadlessException | ClassNotFoundException | SQLException f){    //Handle exception with multiple types
                JOptionPane.showMessageDialog(null,"not success");                  //popup message
            }
     });
    
     edit.addMouseListener(new MouseAdapter()                                       //add Mouse Listener to edit
     {
         @Override
         public void mouseEntered(MouseEvent x)                                     //add Mouse Entered
         {
             edit.setBackground(Color.gray);                                        //set Background color
             edit.setForeground(Color.white);                                       //set font color
         }
         @Override
         public void mouseExited(MouseEvent x)                                      //add Mouse Exited
         {
             edit.setBackground(UIManager.getColor("control"));                     //set Background color
             edit.setForeground(Color.black);                                       //set font color
         }
     
     });
     
     edit.addActionListener((ActionEvent e)->{                                      //add Action Listener to edit
             if(car.isSelected()){                                                  //Checking if car is Selected
                 Trs="Car";                                                         //set Trs value
                 transport_allowance = Basic_Salary * 0.06;                         //formula to count transport_allowance
             }else if(motor.isSelected()){                                          //Checking if Moto is Selected
                 Trs="Motorcycle";                                                  //set Trs value
                 transport_allowance = Basic_Salary * 0.03;                         //formula to count transport_allowance
             }
             
            if(nd.isSelected()){                                                    //Checking if nd is Selected
                OTType = "Normal Day";                                              //set OTType value
            }
            else if(ph.isSelected()){                                               //Checking if pf
                OTType = "Public Holiday";                                          //set OTType value
            }
            
        gross_salary = Basic_Salary + OT + transport_allowance;                     //formula to count gross_salary
        net_income=gross_salary;                                                    //formula to count net_income
        txtGS.setText(String.valueOf(gross_salary));                                //set text to txtGS
        txtNI.setText(String.valueOf(net_income));                                  //set text to txtNI
            
         switch (Clark.getSelectedIndex()) {                                        //swith statement
             case 1 -> Pos=position[1];                                             //set pos value using calling value in position array
             case 2 -> Pos=position[2];                                             //set pos value using calling value in position array
             case 3 -> Pos=position[3];                                             //set pos value using calling value in position array
             default -> JOptionPane.showMessageDialog
                        (null, "Please Choose Your Position");                      //popup message
         }
            try{
                 Class.forName(driver);                                              //Load the JDBC driver
                 Connection kon = DriverManager.getConnection(url, user, pass);      //Establish a database connection
                 Statement stm = kon.createStatement();                              //Create a Statement object
                 
                 String useDBQuery = "USE payroll";                                  //Switch to the payroll database
                 stm.execute(useDBQuery);                                            //execute sql statement
                 
                 Eid=txtEID.getText();
                 Ic=txtIC.getText();
                 Nama=txtName.getText();
                 BS=txtSalary.getText();
                 OverT = String.valueOf(OT);
                 TA = String.valueOf(transport_allowance);
                 GS=txtGS.getText();
                 NI=txtNI.getText();
                 
                        String sql = "UPDATE employee SET Employee_ID = '"          //update syntax form table employee in payroll database
                            + Eid + "', IC = '" + Ic + "', Name = '"
                            + Nama + "', Position = '" + Pos
                            + "', Basic_Salary = '" + BS
                            + "', Overtime_Rate = '" + OverT
                            + "', Overtime_Type = '" + OTType
                            + "', Transport = '" + Trs
                            + "', Transport_Allowance = '" + TA 
                            + "', Gross_Salary = '" + GS 
                            + "', Net_Income = '" + NI
                            + "' WHERE Employee_ID = '" + Eid + "'";

                         int rowsUpdated = stm.executeUpdate(sql);                  //Execute update query and get the number of updated rows
                    
                    if (rowsUpdated > 0) {                                          //if rowsUpdated > 0 , do
                        JOptionPane.showMessageDialog                               //popup message
                        (null, "Data Updated successfully.");
                    } else {                                                        //if rowsUpdated !> 0 , do
                        JOptionPane.showMessageDialog                               //popup message
                        (null, "No rows were updated.");
                    }
                    
                }catch (HeadlessException | ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog                                       //popup message
                (null, "Failed to update data: " + ex.getMessage());
            }
        });
     
     clr.addMouseListener(new MouseAdapter()                                        //add Mouse Listener to clr
     {
         @Override
         public void mouseEntered(MouseEvent x)
         {
             clr.setBackground(Color.gray);                                         //set Background color
             clr.setForeground(Color.white);                                        //set font color
         }
         @Override
         public void mouseExited(MouseEvent x)                                      //add Mouse Exited
         {
             clr.setBackground(UIManager.getColor("control"));                     //set Background color
             clr.setForeground(Color.black);                                       //set font color
         }
     
     });
     
     clr.addActionListener((ActionEvent e)->{                                       //add Action Listener to clr
         txtEID.setText("");                                                        //set text to txtEID
         txtIC.setText("");                                                         //set text to txtIC
         txtName.setText("");                                                       //set text to txtName
         txtGS.setText("");                                                         //set text to txtGS
         txtNI.setText("");                                                         //set text to txtNI
         Clark.setSelectedIndex(0);                                                 //set Select to Clark for index 0
         g.clearSelection();                                                        //clear selection in g
         txtSalary.setText("");                                                     //set text to txtSalary
         nd.setSelected(false);                                                     //Deselecting "nd" option
         ph.setSelected(false);                                                     //Deselecting "ph" option
     });
     search.addMouseListener(new MouseAdapter()                                     //add Mouse Listener to search
     {
         @Override
         public void mouseEntered(MouseEvent x)                                     //add Mouse Entered
         {
             search.setBackground(Color.gray);                                      //set Background color
             search.setForeground(Color.white);                                     //set font color
         }
         @Override
         public void mouseExited(MouseEvent x)                                      //add Mouse Exited
         {
             search.setBackground(UIManager.getColor("control"));                   //set Background color
             search.setForeground(Color.black);                                     //set font color
         }
     
     });
    
     search.addActionListener((ActionEvent e) -> {                                  //add action listener to JButton Search
            String idNumber = JOptionPane.showInputDialog
                        (null, "Enter Employee ID : ");                             //Prompt the user for the ID number
            try {
                Class.forName(driver);                                              //Load the JDBC driver
                try (Connection kon = DriverManager.getConnection                   //Establish a database connection
                (url, user, pass);
                        Statement stn = kon.createStatement()) {                    //Create a Statement object
                    
                    String useDBQuery = "USE payroll";                              //Switch to the payroll database
                    stn.execute(useDBQuery);                                        //execute sql statement
                    
                    String sql = "SELECT * FROM employee WHERE Employee_ID = '" +   //select syntax form table employee in payroll database
                            idNumber + "'";
                    try (ResultSet rs = stn.executeQuery(sql)) {                    //Execute query and manage result
                        if (rs.next()) {                                            //Check if there is a next result
                            txtEID.setText(rs.getString("Employee_ID"));            //Set text of txtEID field
                            txtIC.setText(rs.getString("IC"));                      //Set text of txtIC field
                            txtName.setText(rs.getString("Name"));                  //Set text of txtName field
                            String pos1 = rs.getString("Position");                 //Retrieve value of "Position" column
                            switch (pos1) {                                         //switch statement
                                case "Executive" -> Clark.setSelectedIndex(1);      //Set Clark Selected to index 1
                                case "Manager" -> Clark.setSelectedIndex(2);        //Set Clark Selected to index 2
                                case "Operation and Production" -> 
                                    Clark.setSelectedIndex(3);                      //Set Clark Selected to index 3
                                default -> {
                                }
                            }
                            txtSalary.setText(rs.getString("Basic_Salary"));        //Set text of txtSalary field
                            String ot1=rs.getString("Overtime_Type");               //Retrieve value of "Overtime_Type" column
                            switch (ot1) {                                          //switch statement
                                case "Normal Day" -> {
                                    nd.setSelected(true);                           //Set nd to selected
                                    ph.setSelected(false);                          //Set ph to deselected
                                }
                                case "Public Holiday" -> {
                                    nd.setSelected(false);                          //Set nd to deselected
                                    ph.setSelected(true);                           //Set ph to selected
                                }
                                default -> {
                                }
                            }
                            String trns=rs.getString("Transport");                  //Retrieve value of "Transport" column
                            switch (trns) {                                         //switch statement
                                case "Car" -> {
                                    car.setSelected(true);                          //Set car to selected
                                    motor.setSelected(false);                       //Set motor to deselected
                                }
                                    case "Motorcycle" ->  {
                                    car.setSelected(false);                          //Set car to deselected
                                    motor.setSelected(true);                        //Set motor to selected
                                }
                                default -> {
                                }
                            }
                            txtGS.setText(rs.getString                              //Set text of txtGS field
                                                ("Gross_Salary"));
                            txtNI.setText(rs.getString("Net_Income"));              //Set text of txtGS field
                        } else {                                                    //else statement
                            JOptionPane.showMessageDialog                           //popup Message
                            (null,"Participant not found.");
                        }
                    }
                }
            }catch (HeadlessException | ClassNotFoundException | SQLException ex) { //Catch and handle exceptions
                JOptionPane.showMessageDialog                                       //popup message
                (null, "Failed to retrieve participant data: " 
                        + ex.getMessage());
            }
        });
     
}

  
    public static void main(String[] args) {
       PayrollSystem sys = new PayrollSystem();
    }
    
}
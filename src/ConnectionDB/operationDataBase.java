/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import applicationmaster.Tools;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author benso
 */
public class operationDataBase {
 public static void FillTo_TableView(JTable table){
        ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
        try{
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            DBCollection col=(DBCollection) ConnectionDB.MongoDB.db.getCollection("taxi1");
            DBCursor cursor=col.find();
      int   i=0;
            
            while(cursor.hasNext() && i!=1000000 ){
            DBObject dbo=cursor.next();
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID;
            
            try{
             vndID=(int) dbo.get("VendorID");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("VendorID");
            vndID=(int) a;
            }
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
           
         
          
           /* String tf=String.valueOf(Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))-Integer.parseInt(tpepdate.substring(11, 19).substring(0, 2))
            +":"+String.valueOf(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))-Integer.parseInt(tpepdate.substring(11, 19).substring(3,5 ))+":"+
                    String.valueOf(Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8))-Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8)))));
           */
                String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);
                
                int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            double tam=1.0;
            try{
            tam=(double)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("tip_amount");
            tam*=a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
            String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});
        // model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});
            }    
       table.setModel(model);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
 // fonction pour l user 
    public static boolean checkUser(String Password)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
    DBCollection collection =ConnectionDB.MongoDB.db.getCollection("users");
    DBCursor cursor=collection.find();
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    if(dbo.get("Password").equals(Password))
    {
    return true;
    }
    else{
    return false;
    }
    }
    return false;  
    }
    public static void insertUsers(String userName,String Password)
    {
   ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
    DBCollection collection=ConnectionDB.MongoDB.db.getCollection("users");
    BasicDBObject doc=new BasicDBObject();
    doc.append("userName", "admin");
    doc.append("Password", "admin");
    collection.insert(doc);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  username 
    public static void fillComboBox(JComboBox combo)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
    DBCollection col=  ConnectionDB.MongoDB.db.getCollection("users");
        DBCursor cursor=col.find(new BasicDBList(),new BasicDBObject("userName",Boolean.TRUE));
        while(cursor.hasNext()){
        combo.addItem((String)cursor.next().get("userName"));
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search data  pour dlocation plocation 
     public static void searchData(JTable table,String Column,int val)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
   
   
   int val2=(int)dbo.get(Column);
if(val2<val)
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
               String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
    if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("there is no erroneous recording ");
    }
    else{
    table.setModel(model);
    }
  
    
    
    }
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search data 2 
     // c pour  ll vindid+ psscount +paymment type
    public static void searchData2(JTable table,String Column,int val)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
   
   
   int val2=(int)dbo.get(Column);
if(val2>val)
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
       String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);        
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
    if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
    
    
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search data 3 pour 
    // improvment surcharge +Mta_Tax   // double 
     public static void searchData3(JTable table,String Column,double val)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
    double val2=1.0;
   try{
   val2=(double)dbo.get(Column);
   }
   catch(Exception ex)
   {
   int a=(int) dbo.get(Column);
   val2*=a;
   }
  
if(val2!=val) // && val!=0.5
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
       String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);        
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
   if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
    
    
    }
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search data 4 total amount + trip distance 
    public static void searchData4(JTable table,String Column,double val)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
    double val2=1.0;
   try{
   val2=(double)dbo.get(Column);
   }
   catch(Exception ex)
   {
   int a=(int) dbo.get(Column);
   val2*=a;
   }
  
if(val2<val) // && val!=0.5
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
              String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf); 
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }    
    
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search data 6 pour extra 
     public static void searchData6(JTable table,String Column,double val,double val1,double val3)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
    double val2=1.0;
   try{
   val2=(double)dbo.get(Column);
   }
   catch(Exception ex)
   {
   int a=(int) dbo.get(Column);
   val2*=a;
   }
  
if(val2!=val && val2!=val1 && val2!=val3) // && val!=0.5
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
    
            String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);        
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
    if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
    
    
    }
   /*  public static int getNumInteger(JTable table,String Column,int min,int max)
    {
        int i=0;
         ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
        DBCollection col= ConnectionDB.MongoDB.db.getCollection("taxi1");
          DBCursor cursor=col.find();
          while(cursor.hasNext()){
            DBObject dbo= cursor.next();
            int vndID=(int) dbo.get(Column);
           if(vndID<min || vndID>max)
           {
               
           i++;
           }     
        }
    return i;
    }*/
     public static int getcharInteger(String Column,String var1,String var2)
    {
        int i=0;
        ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
        DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
          DBCursor cursor=col.find();
          while(cursor.hasNext()){
            DBObject dbo= cursor.next();
            String S_A_F=(String) dbo.get(Column);
           if(!S_A_F.equals(var1) || !S_A_F.equals(var2));
           {
           i++;
           }
              
        }
    return i;
    }
     /////////////////////////////////////////////////////////////////////////////////////////////////////////
    int i=0; 
    public  void fillFautSearch(JTable table,Vector<ObjectId> list)
    {
        
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    
  try{
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
   
  
if(list.get(i).equals(dbo.get("_id"))) // && val!=0.5
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
            String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
  }
  catch(Exception ex){
  Tools.showMessage(i+"");
  }
    if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
    
    
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
     public static double getAver(JTable table,int indexColumn)
     {
         double Res=0;
      for(int i=0;i<table.getModel().getRowCount();i++)
      {
          try{
     Res+=(double) table.getModel().getValueAt(i, indexColumn);
          }
          catch(Exception ex)
          {
          int a=(int) table.getModel().getValueAt(i, indexColumn);
          Res+=a;
          }
      }
     
     return  Res/table.getModel().getRowCount();
     }
     public static void istogrameDist(int min,int max,String Column,JTable table)
     {
        
          ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
   
   
   int val2=(int)dbo.get(Column);
if( min>(int)dbo.get(Column) || (int)dbo.get(Column)>max)
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
             
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
     if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
  
    
     }
      public static void istogrameDistDB(double min,double max,String Column,JTable table)
     {
        
          ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
   double val=1.0;
   try{
   val =(double)dbo.get(Column);
   }
   catch(Exception ex)
   {
   int a=(int)dbo.get(Column);
   val*=a;
   }
   
//   int val2=(int)dbo.get(Column);
if( min>val || val>max)
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
             
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
     if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
  
    
     }
      public static void getObjectID(JTable table1,JTable table2)
     {
         DefaultTableModel model=(DefaultTableModel) table2.getModel();
         Vector<ObjectId> vec=new Vector<ObjectId>();
        
        for(int i=0;i<table1.getModel().getRowCount();i++)
        {
        vec.add((ObjectId) table1.getModel().getValueAt(i, 0));
        }
        for(int i=0;i<table1.getModel().getRowCount();i++)
        {
        model.addRow(new Object[]{vec.get(i)});
        }
        table2.setModel(model);
     }
      
    /*  public static void loadMatrix(JTable tabDelete,JTable tab1,JTable tab2,JTable tab3,JTable tab4,JTable tab5,JTable tab6,JTable tab7,JTable Matrix)
      {
          DefaultTableModel model=(DefaultTableModel) Matrix.getModel();
          boolean t1=true,t2=true,t3=true,t4=true,t5=true,t6=true,t7=true,t8=true;
         int a=0,b=0,c=0,d=0,e=0,f=0,g=0;  
          Vector<Integer> vec=new Vector<Integer>();
          for(int i=0;i<tabDelete.getModel().getRowCount();i++)
          {
              ObjectId id=(ObjectId) tabDelete.getModel().getValueAt(i, 0);
           for(int j=0;j<tab1.getModel().getRowCount();j++)
           {
            if((id.equals(tab1.getModel().getValueAt(j, 0)) && j+1!=tab1.getModel().getRowCount() )&& t1)
            {
            //      vec.add(1);
                a=1;
              t1=false; //bah tab y3mer 7 mayzidche 
            }
            else if((!id.equals(tab1.getModel().getValueAt(j, 0)) && j+1==tab1.getModel().getRowCount()) && t1)
            {
                a=0;
            //vec.add(0);
           
            }
           }
           
           for(int j=0;j<tab2.getModel().getRowCount();j++)
           {
           if((id.equals(tab2.getModel().getValueAt(j, 0)) && j+1!=tab2.getModel().getRowCount() )&& t2)
            {
                b=1;
              //vec.add(1);
              t2=false;
            }
            else if((!id.equals(tab2.getModel().getValueAt(j, 0)) && j+1==tab2.getModel().getRowCount()) && t2)
            {
                b=0;
           // vec.add(0);
            }
           }
           
           for(int j=0;j<tab3.getModel().getRowCount();j++)
           {
           if((id.equals(tab3.getModel().getValueAt(j, 0)) && j+1!=tab3.getModel().getRowCount() )&& t3)
            {
                c=1;
             // vec.add(1);
              t3=false;
            }
            else if((!id.equals(tab3.getModel().getValueAt(j, 0)) && j+1==tab3.getModel().getRowCount()) && t3)
            {
                c=0;
            //vec.add(0);
            }
           }for(int j=0;j<tab4.getModel().getRowCount();j++)
           {
           if((id.equals(tab4.getModel().getValueAt(j, 0)) && j+1!=tab4.getModel().getRowCount() )&& t4)
            {
                d=1;
              //vec.add(1);
              t4=false;
            }
            else if((!id.equals(tab4.getModel().getValueAt(j, 0)) && j+1==tab4.getModel().getRowCount()) && t4)
            {
                d=0;
            //vec.add(0);
            }
           }for(int j=0;j<tab5.getModel().getRowCount();j++)
           {
           if((id.equals(tab5.getModel().getValueAt(j, 0)) && j+1!=tab5.getModel().getRowCount() )&& t5)
            {
                e=1;
              //vec.add(1);
              t5=false;
            }
            else if((!id.equals(tab5.getModel().getValueAt(j, 0)) && j+1==tab5.getModel().getRowCount()) && t5)
            {
                e=0;
           // vec.add(0);
            }
           }for(int j=0;j<tab6.getModel().getRowCount();j++)
           {
           if((id.equals(tab6.getModel().getValueAt(j, 0)) && j+1!=tab6.getModel().getRowCount() )&& t6)
            {
                f=1;
              //vec.add(1);
              t6=false;
            }
            else if((!id.equals(tab6.getModel().getValueAt(j, 0)) && j+1==tab6.getModel().getRowCount()) && t6)
            {
                f=0;
            vec.add(0);
            }
           
           }for(int j=0;j<tab7.getModel().getRowCount();j++)
           {
           if((id.equals(tab7.getModel().getValueAt(j, 0)) && j+1!=tab7.getModel().getRowCount() )&& t7)
            {
                g=1;
              //vec.add(1);
              t7=false;
            }
            else if((!id.equals(tab7.getModel().getValueAt(j, 0)) && j+1==tab7.getModel().getRowCount()) && t7)
            {
                g=0;
            //vec.add(0);
            }
           }
           t1=true;
           t2=true;
           t3=true;
           t4=true;
           t5=true;
           t6=true;
           t7=true;
           for(int j=0;j<tab8.getModel().getRowCount();j++)
           {
           if(id.equals(tab8.getModel().getValueAt(j, 0)) && t8)
            {
              vec.add(1);
              t8=false;
            }
            else if(!id.equals(tab8.getModel().getValueAt(j, 0)) && t8)
            {
            vec.add(0);
            }
           }
           model.addRow(new Object[]{a,b,c,d,e,f,g});
          }
          Matrix.setModel(model);
      
      }*/
      public static void loadMatrix(JTable tab1,JTable tab2,JTable tab3,JTable tab4,JTable tab5,JTable tab6,JTable tab7,JTable Matrix)
      {
      DefaultTableModel model=(DefaultTableModel) Matrix.getModel();
      for(int i=0;i<tab1.getModel().getRowCount();i++)
      {
      Matrix.getModel().setValueAt((String)tab1.getModel().getValueAt(i, 0), i, 0);
      }
      
      }
        
}

package databaseProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class MyDatabase{
	// Create the index file
	static RandomAccessFile dbFile;
	static File idFile=new File("id.ndx");
	static File companyFile = new File("company.ndx");
	static File drug_idFile=new File("drug_id.ndx");
	static File trialsFile=new File("trials.ndx");
	static File patientsFile=new File("patients.ndx");
	static File dosage_mgFile=new File("dosage_mg.ndx");
	static File readingFile=new File("reading.ndx");
	static File double_blindFile=new File("double_blind.ndx");
	static File controlled_studyFile=new File("controlled_study.ndx");
	static File govt_fundedFile=new File("govt_funded.ndx");
	static File fda_approvedFile=new File("fda_approved.ndx");

	static TreeMap<Integer,ArrayList<Integer>> idTreeMap=new TreeMap();
	static TreeMap<String,ArrayList<Integer>> compTreeMap=new TreeMap();
	static TreeMap<String,ArrayList<Integer>> drugTreeMap=new TreeMap();
	static TreeMap<Short,ArrayList<Integer>> trialsTreeMap=new TreeMap();
	static TreeMap<Short,ArrayList<Integer>> patientsTreeMap=new TreeMap();
	static TreeMap<Short,ArrayList<Integer>> dosageTreeMap=new TreeMap();
	static TreeMap<Float,ArrayList<Integer>> readingTreeMap=new TreeMap();
	static TreeMap<String,ArrayList<Integer>> doubleTreeMap=new TreeMap();
	static TreeMap<String,ArrayList<Integer>> controlledTreeMap=new TreeMap();
	static TreeMap<String,ArrayList<Integer>> govtTreeMap=new TreeMap();
	static TreeMap<String,ArrayList<Integer>> fdaTreeMap=new TreeMap();	
	
	public static void  main(String [] args) throws IOException{

			System.out.println("Menu of possible commands : ");
			System.out.println("1.Import");
			System.out.println("2.Query");
			System.out.println("3.Insert");
			System.out.println("4.Delete");
			System.out.print("Please select the option number : ");
			Scanner scanner = new Scanner(System.in);
			int input0 = scanner.nextInt();
	
		if (input0 == 1) {
			// Specifies the format of a CSV file and parses input.
			Reader csvFile=new FileReader("PHARMA_TRIALS_1000B.csv");
			Iterable<CSVRecord> records=CSVFormat.EXCEL.parse(csvFile);
			int count=0;
			try{
				for(CSVRecord record:records){
					if(count>0){
					String [] data=new String[11];
					data[0]=record.get(0);
					data[1]=record.get(1);
					data[2]=record.get(2);
					data[3]=record.get(3);
					data[4]=record.get(4);
					data[5]=record.get(5);
					data[6]=record.get(6);
					data[7]=record.get(7);
					data[8]=record.get(8);
					data[9]=record.get(9);
					data[10]=record.get(10);
					// write each record into the data file
					insert(data);
					
				}
					else{
						++count;
					}
			}			
				writeIntIndexFile(idTreeMap,idFile);
				writeStringIndexFile(compTreeMap,companyFile);
				writeStringIndexFile(drugTreeMap,drug_idFile);
				writeShortIndexFile(trialsTreeMap,trialsFile);
				writeShortIndexFile(patientsTreeMap,patientsFile);
				writeShortIndexFile(dosageTreeMap,dosage_mgFile);
				writeFloatIndexFile(readingTreeMap,readingFile);
				writeStringIndexFile(doubleTreeMap,double_blindFile);
				writeStringIndexFile(controlledTreeMap,controlled_studyFile);
				writeStringIndexFile(govtTreeMap,govt_fundedFile);
				writeStringIndexFile(fdaTreeMap,fda_approvedFile);
			}
			catch(FileNotFoundException e){
				System.out.println("File not found, please check the file name again!");
			} 
		

			System.out.println("choose an attribute to make a query:"+"\n"+"1. id"+"\n"+"2. company"+"\n"+"3. drug_id"+"\n"+"4. trials"+"\n"+"5. patients"+"\n"+"6. dosage_mg"+"\n"+"7. reading"+"\n"+"8. double_blind"+"\n"+"9. controlled_study"+"\n"+"10.govt_funded"+"\n"+"11.fda_approved"+"\n"+"12.exit"+"\n");		
			Scanner sc=new Scanner(System.in);
			int input =sc.nextInt();
			switch(input){
			case 1:  System.out.println("please type ID to make a query :"+"\n");
			         Scanner id=new Scanner(System.in);
			         int idNum=id.nextInt();
			         System.out.println("choose the operator:"+"\n"+"1. >"+"\n"+"2. >="+"\n"+"3. ="+"\n"+"4. NOT"+"\n"+"5. <="+"\n"+"6. <");
			         Scanner operator=new Scanner(System.in);
			         int opNum=operator.nextInt();
			         intGetAddress(idNum,idTreeMap,opNum);
			         break;
			        
			case 2: System.out.println("please type company to make a query :"+"\n");
			 		Scanner company1=new Scanner(System.in);
			 		String company=company1.nextLine();	 		
			 		System.out.println("choose the operator:"+"\n"+"1. >"+"\n"+"2. >="+"\n"+"3. ="+"\n"+"4.!="+"\n"+"5. <="+"\n"+"6. <'"+"\n");	 		
			        Scanner operator1=new Scanner(System.in);
			        int opcom=operator1.nextInt();
			        stringGetAddress(company,compTreeMap,opcom);
			        break;
			case 3: System.out.println("please type drug_id to make a query : ");
					Scanner drugID=new Scanner(System.in);
					String drug_id=drugID.nextLine();
					System.out.println("choose the operator:"+"\n"+"1. >"+"\n"+"2. >="+"\n"+"3. ="+"\n"+"4.!="+"\n"+"5. <="+"\n"+"6. <'"+"\n");	 
					Scanner operator2=new Scanner(System.in);
					int opdrug=operator2.nextInt();
					stringGetAddress(drug_id,drugTreeMap,opdrug);
					break;
			case 4: System.out.println("please type trials to make a query : ");
					Scanner trials1=new Scanner(System.in);
			 		Short trials=trials1.nextShort();	 		
			 		System.out.println("choose the operator:"+"\n"+"1. >"+"\n"+"2. >="+"\n"+"3. ="+"\n"+"4.!="+"\n"+"5. <="+"\n"+"6. <'"+"\n");	 		
			        Scanner operator3=new Scanner(System.in);
			        int optrial=operator3.nextInt();
			        shortGetAddress(trials,trialsTreeMap,optrial);
			        break;
			case 5: System.out.println("please type patients to make a query : ");
					Scanner patients1=new Scanner(System.in);
			 		Short patients=patients1.nextShort();	 		
			 		System.out.println("choose the operator:"+"\n"+"1. >"+"\n"+"2. >="+"\n"+"3. ="+"\n"+"4.!="+"\n"+"5. <="+"\n"+"6. <'"+"\n");	 		
			        Scanner operator4=new Scanner(System.in);
			        int oppatients=operator4.nextInt();
			        shortGetAddress(patients,patientsTreeMap,oppatients);
			        break;
			case 6: System.out.println("please type dosage_mg to make a query : ");
					Scanner dosage_mg1=new Scanner(System.in);
			 		Short dosage_mg=dosage_mg1.nextShort();	 		
			 		System.out.println("choose the operator:"+"\n"+"1. >"+"\n"+"2. >="+"\n"+"3. ="+"\n"+"4.!="+"\n"+"5. <="+"\n"+"6. <'"+"\n");	 		
			        Scanner operator5=new Scanner(System.in);
			        int opdosage=operator5.nextInt();
			        shortGetAddress(dosage_mg,dosageTreeMap,opdosage);  
			        break;
			case 7: System.out.println("please type reading to make a query : ");
					Scanner reading1=new Scanner(System.in);
					Float reading=reading1.nextFloat();	 		
			 		System.out.println("choose the operator:"+"\n"+"1. >"+"\n"+"2. >="+"\n"+"3. ="+"\n"+"4.!="+"\n"+"5. <="+"\n"+"6. <'"+"\n");	 		
			        Scanner operator6=new Scanner(System.in);
			        int opreading=operator6.nextInt();
			        floatGetAddress(reading,readingTreeMap,opreading);
			        break;
			case 8:System.out.println("please type double_blind to make a query :");
					Scanner double_blind1=new Scanner(System.in);
					String double_blind=double_blind1.nextLine();
					System.out.println("choose the operator:"+"\n"+"1. ="+"\n"+"2. !="+"\n");	 
					Scanner operator7=new Scanner(System.in);
					int opdouble=operator7.nextInt();
					booleanGetAddress(double_blind,doubleTreeMap,opdouble);
					break;
			case 9:System.out.println("please type controlled_study to make a query:");
					Scanner controlled1=new Scanner(System.in);
					String controlled=controlled1.nextLine();
					System.out.println("choose the operator:"+"\n"+"1. ="+"\n"+"2. !="+"\n");	 
					Scanner operator8=new Scanner(System.in);
					int opcontrolled=operator8.nextInt();
					booleanGetAddress(controlled,controlledTreeMap,opcontrolled);
					break;
			case 10:System.out.println("please type govt_funded to make a query :");
					Scanner govt1=new Scanner(System.in);
					String govt=govt1.nextLine();
					System.out.println("choose the operator:"+"\n"+"1. ="+"\n"+"2. !="+"\n");	 
					Scanner operator9=new Scanner(System.in);
					int opgovt=operator9.nextInt();
					booleanGetAddress(govt,govtTreeMap,opgovt);
					break;
			case 11:System.out.println("please type fda_approved to make a query :");
					Scanner fda1=new Scanner(System.in);
					String fda=fda1.nextLine();
					System.out.println("choose the operator:"+"\n"+"1. ="+"\n"+"2. !="+"\n");	 
					Scanner operator10=new Scanner(System.in);
					int opfda=operator10.nextInt();
					booleanGetAddress(fda,govtTreeMap,opfda);
					break;
			case 12:System.out.println("Program terminated.");
		      		System.exit(0);
			default:
			      	System.out.println("Invalid input, please select again !");
			         }
		}
		else if (input0 ==2){
			System.out.println("Please import the data file first to execute query,insert or delete commands, program terminated.");
			System.exit(0);
		}
		else if (input0==3){
			insertNew();
		}
		else if (input0==4){
			Scanner id=new Scanner(System.in);
			System.out.println("Please type the id to delete the corresponding row ?");
			int deleteId =id.nextInt();
			delete(deleteId);
		}
		
		else{
			System.out.println("Please type a valid option number, program terminated.");
			System.exit(0);
		}
		
	}
	public static void insertNew() throws IOException{
		System.out.println("Please insert the new record into the data file");
        Scanner sc = new Scanner(System.in);
        
		String [] dataNew=new String[11];
        
        System.out.print("ID: (integer, starting from 1001 please): ");
        String id = sc.next();
        dataNew[0]= id;
        
        System.out.print("company: ");
        String company = sc.next();
        dataNew[1]= company;
        
        System.out.print("drug_id: ");
        String drug_id = sc.next();
        dataNew[2]= drug_id;
        
        System.out.print("trials: ");
        String trials = sc.next();
        dataNew[3]=trials;
        
        System.out.print("patients: ");
        String patients = sc.next();
        dataNew[4]= patients;
        
        System.out.print("dosage_mg: ");
        String dosage_mg = sc.next();
        dataNew[5]= dosage_mg;
        
        System.out.print("reading: ");
        String reading = sc.next();
        dataNew[6]= dosage_mg;
        
        System.out.print("double_blind (TRUE OR FALSE): ");
        String double_blind = sc.next();
        dataNew[7]=double_blind;
        
        System.out.print("controlled_study (TRUE OR FALSE): ");
        String controlled_study = sc.next();
        dataNew[8]=controlled_study;
        
        System.out.print("govt_funded (TRUE OR FALSE): ");
        String govt_funded = sc.next();
        dataNew[9]=govt_funded;
        
        System.out.print("fda_approved (TRUE OR FALSE): ");
        String fda_approved = sc.next();
        dataNew[10]=fda_approved;  
        
        Reader cvsFile=new FileReader("PHARMA_TRIALS_1000B.csv");
		Iterable<CSVRecord> records=CSVFormat.EXCEL.parse(cvsFile);
		int count=0;
		try{
			for(CSVRecord record:records){
				if(count>0){
				String [] data=new String[11];
				data[0]=record.get(0);
				data[1]=record.get(1);
				data[2]=record.get(2);
				data[3]=record.get(3);
				data[4]=record.get(4);
				data[5]=record.get(5);
				data[6]=record.get(6);
				data[7]=record.get(7);
				data[8]=record.get(8);
				data[9]=record.get(9);
				data[10]=record.get(10);
				// write each record into the data file
				insert(data);
				
			}
				else{
					++count;
				}
		}	
			insert(dataNew);
			
			writeIntIndexFile(idTreeMap,idFile);
			writeStringIndexFile(compTreeMap,companyFile);
			writeStringIndexFile(drugTreeMap,drug_idFile);
			writeShortIndexFile(trialsTreeMap,trialsFile);
			writeShortIndexFile(patientsTreeMap,patientsFile);
			writeShortIndexFile(dosageTreeMap,dosage_mgFile);
			writeFloatIndexFile(readingTreeMap,readingFile);
			writeStringIndexFile(doubleTreeMap,double_blindFile);
			writeStringIndexFile(controlledTreeMap,controlled_studyFile);
			writeStringIndexFile(govtTreeMap,govt_fundedFile);
			writeStringIndexFile(fdaTreeMap,fda_approvedFile);
		}
		catch(FileNotFoundException e){
			System.out.println("File not found, please check the file name again!");
		}
 
//        
//        BufferedWriter out = new BufferedWriter(new FileWriter("PHARMA_TRIALS_1000B.csv",true));
//        out.write("\n" + id + "," + company + "," + drug_id + "," + trials + "," + patients + "," + dosage_mg + "," + reading+"," + double_blind +","+ controlled_study + "," + govt_funded +"," + fda_approved);
//        out.newLine();
//        out.close();
//        
//        Reader cvsFile=new FileReader("PHARMA_TRIALS_1000B.csv");
//		Iterable<CSVRecord> records=CSVFormat.EXCEL.parse(cvsFile);
//		int count=0;
//		try{
//			for(CSVRecord record:records){
//				if(count>0){
//				String [] data=new String[11];
//				data[0]=record.get(0);
//				data[1]=record.get(1);
//				data[2]=record.get(2);
//				data[3]=record.get(3);
//				data[4]=record.get(4);
//				data[5]=record.get(5);
//				data[6]=record.get(6);
//				data[7]=record.get(7);
//				data[8]=record.get(8);
//				data[9]=record.get(9);
//				data[10]=record.get(10);
//				// write each record into the data file
//				insert(data);
//			}
//				else{
//					++count;
//				}
//		}			
			writeIntIndexFile(idTreeMap,idFile);
			writeStringIndexFile(compTreeMap,companyFile);
			writeStringIndexFile(drugTreeMap,drug_idFile);
			writeShortIndexFile(trialsTreeMap,trialsFile);
			writeShortIndexFile(patientsTreeMap,patientsFile);
			writeShortIndexFile(dosageTreeMap,dosage_mgFile);
			writeFloatIndexFile(readingTreeMap,readingFile);
			writeStringIndexFile(doubleTreeMap,double_blindFile);
			writeStringIndexFile(controlledTreeMap,controlled_studyFile);
			writeStringIndexFile(govtTreeMap,govt_fundedFile);
			writeStringIndexFile(fdaTreeMap,fda_approvedFile);


	}
	
	public static void delete(int deleteId) throws IOException{
		Reader cvsFile=new FileReader("PHARMA_TRIALS_1000B.csv");
		Iterable<CSVRecord> records=CSVFormat.EXCEL.parse(cvsFile);
		int count=0;
		try{
			for(CSVRecord record:records){
				if(count>0){
				String [] data=new String[11];
				data[0]=record.get(0);
				data[1]=record.get(1);
				data[2]=record.get(2);
				data[3]=record.get(3);
				data[4]=record.get(4);
				data[5]=record.get(5);
				data[6]=record.get(6);
				data[7]=record.get(7);
				data[8]=record.get(8);
				data[9]=record.get(9);
				data[10]=record.get(10);
				// write each record into the data file
				insert2(deleteId,data);
			}
				else{
					++count;
				}
		}			
			writeIntIndexFile(idTreeMap,idFile);
			writeStringIndexFile(compTreeMap,companyFile);
			writeStringIndexFile(drugTreeMap,drug_idFile);
			writeShortIndexFile(trialsTreeMap,trialsFile);
			writeShortIndexFile(patientsTreeMap,patientsFile);
			writeShortIndexFile(dosageTreeMap,dosage_mgFile);
			writeFloatIndexFile(readingTreeMap,readingFile);
			writeStringIndexFile(doubleTreeMap,double_blindFile);
			writeStringIndexFile(controlledTreeMap,controlled_studyFile);
			writeStringIndexFile(govtTreeMap,govt_fundedFile);
			writeStringIndexFile(fdaTreeMap,fda_approvedFile);
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
	
	}
	
	private static void booleanGetAddress(String s, TreeMap<String,ArrayList<Integer>> map, int operator) throws IOException{
		//NavigableMap<String, ArrayList<Integer>> submap1=new TreeMap<String,ArrayList<Integer>>();
		switch(operator){
		case 1:ArrayList<Integer> address=map.get(s);
		  readbyte(address);
		  break;
		case 2:String a="true";
		String b="false";
		if(s.equals(a)){
			ArrayList<Integer> address1=map.get(b);
			 readbyte(address1);
		}
		else{
			ArrayList<Integer> address1=map.get(a);
		  readbyte(address1);
		  break;
		}
		}
	}
	private static void floatGetAddress(float s, TreeMap<Float,ArrayList<Integer>> map, int operator) throws IOException{
		 NavigableMap<Float, ArrayList<Integer>> submap1=new TreeMap<Float,ArrayList<Integer>>();
		 NavigableMap<Float, ArrayList<Integer>> submap2=new TreeMap<Float,ArrayList<Integer>>();
		switch(operator){
		case 1:submap1=map.subMap(s,false,map.lastKey(),true);
				for(Entry<Float, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address=entry.getValue();
					readbyte(address);
				}
				break;
		case 2:submap1=map.subMap(s,true,map.lastKey(),true);
				for(Entry<Float, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address=entry.getValue();
					readbyte(address);
				}
				break;
		case 3: ArrayList<Integer> address=map.get(s);
				  readbyte(address);
				  break;
		case 4:submap1=map.subMap(map.firstKey(),true,s,false);
				for(Entry<Float, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				submap2=map.subMap(s,false,map.lastKey(),true);
				for(Entry<Float, ArrayList<Integer>> entry:submap2.entrySet()){
					ArrayList<Integer> address2=entry.getValue();
					readbyte(address2);
				}
				break;
		case 5:submap1=map.subMap(map.firstKey(),true,s,true);
				for(Entry<Float, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				break;
		case 6:submap1=map.subMap(map.firstKey(),true,s,false);
				for(Entry<Float, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				break;
		}
	}
	private static void shortGetAddress(short s, TreeMap<Short,ArrayList<Integer>> map, int operator) throws IOException{
		 NavigableMap<Short, ArrayList<Integer>> submap1=new TreeMap<Short,ArrayList<Integer>>();
		 NavigableMap<Short, ArrayList<Integer>> submap2=new TreeMap<Short,ArrayList<Integer>>();
		switch(operator){
		case 1:submap1=map.subMap(s,false,map.lastKey(),true);
				for(Entry<Short, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address=entry.getValue();
					readbyte(address);
				}
				break;
		case 2:submap1=map.subMap(s,true,map.lastKey(),true);
				for(Entry<Short, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address=entry.getValue();
					readbyte(address);
				}
				break;
		case 3: ArrayList<Integer> address=map.get(s);
				  readbyte(address);
				  break;
		case 4:submap1=map.subMap(map.firstKey(),true,s,false);
				for(Entry<Short, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				submap2=map.subMap(s,false,map.lastKey(),true);
				for(Entry<Short, ArrayList<Integer>> entry:submap2.entrySet()){
					ArrayList<Integer> address2=entry.getValue();
					readbyte(address2);
				}
				break;
		case 5:submap1=map.subMap(map.firstKey(),true,s,true);
				for(Entry<Short, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				break;
		case 6:submap1=map.subMap(map.firstKey(),true,s,false);
				for(Entry<Short, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				break;
		}
	}
	public static void  insert(String[] data) throws IOException{
		dbFile=new RandomAccessFile("PHARMA_TRIALS_1000B.db","rw");
		//write to the db file
		dbFile.seek(dbFile.length());
		int address=(int) dbFile.getFilePointer();;
		// write id
		int id=Integer.parseInt(data[0]);
		// write company
		dbFile.writeInt(id);
		int len=data[1].length();
		dbFile.writeByte(len);
		dbFile.writeBytes(data[1]);
		// write drug_id
		dbFile.writeBytes(data[2]);
		// write trails, patients and dosage_mg
		for(int i=3;i<6;i++){
			short s=Short.parseShort(data[i]);
			dbFile.writeShort(s);
		}
		// write reading
		float f=Float.parseFloat(data[6]);
		dbFile.writeFloat(f);	
		// write  double_blind,controlled_study,govt_funded and fda_approved
		Byte b=(byte) 10000000;
		
		if(Boolean.parseBoolean(data[7]))
		b = (byte) (b | (1 << 3));			
		if (Boolean.parseBoolean(data[8]))	
		b = (byte) (b | (1 << 2));			
		if (Boolean.parseBoolean(data[9]))
		b = (byte) (b | (1 << 1));			
		if (Boolean.parseBoolean(data[10]))	
		b = (byte) (b | (1 << 0));
		dbFile.write(b);					
		if(idTreeMap.containsKey(id)){
			idTreeMap.get(id).add(address);
		}
		else{
			idTreeMap.put(id, new ArrayList<Integer>());
			idTreeMap.get(id).add(address);
		}
		if(compTreeMap.containsKey(data[1])){
			compTreeMap.get(data[1]).add(address);
		}
		else{
			compTreeMap.put(data[1],new ArrayList<Integer>());
			compTreeMap.get(data[1]).add(address);
		}
		if(drugTreeMap.containsKey(data[2])){
			drugTreeMap.get(data[2]).add(address);
		}
		else{
			drugTreeMap.put(data[2], new ArrayList<Integer>());
			drugTreeMap.get(data[2]).add(address);

		}
		short trials=Short.parseShort(data[3]);
		if(trialsTreeMap.containsKey(trials)){
			trialsTreeMap.get(trials).add(address);
		}
		else{
			trialsTreeMap.put(trials, new ArrayList<Integer>());
			trialsTreeMap.get(trials).add(address);
		}
		short patients=Short.parseShort(data[4]);
		if(patientsTreeMap.containsKey(patients)){
			patientsTreeMap.get(patients).add(address);
		}
		else{
			patientsTreeMap.put(patients, new ArrayList<Integer>());
			patientsTreeMap.get(patients).add(address);
		}
		short dosage=Short.parseShort(data[5]);
		if(dosageTreeMap.containsKey(dosage)){
			dosageTreeMap.get(dosage).add(address);
		}
		else{
			dosageTreeMap.put(dosage, new ArrayList<Integer>());
			dosageTreeMap.get(dosage).add(address);

		}
		float reading=Float.parseFloat(data[6]);
		if(readingTreeMap.containsKey(reading)){
			readingTreeMap.get(reading).add(address);
		}
		else{
			readingTreeMap.put(reading, new ArrayList<Integer>());
			readingTreeMap.get(reading).add(address);
		}
		if(doubleTreeMap.containsKey(data[7])){
			doubleTreeMap.get(data[7]).add(address);
		}
		else{
			doubleTreeMap.put(data[7], new ArrayList<Integer>());
			doubleTreeMap.get(data[7]).add(address);

		}
		if(controlledTreeMap.containsKey(data[8])){
			controlledTreeMap.get(data[8]).add(address);
		}
		else{
			controlledTreeMap.put(data[8], new ArrayList<Integer>());
			controlledTreeMap.get(data[8]).add(address);

		}
		if(govtTreeMap.containsKey(data[9])){
			govtTreeMap.get(data[9]).add(address);
		}
		else{
			govtTreeMap.put(data[9], new ArrayList<Integer>());
			govtTreeMap.get(data[9]).add(address);
		}
		if(fdaTreeMap.containsKey(data[10])){
			fdaTreeMap.get(data[10]).add(address);
		}
		else{
			fdaTreeMap.put(data[10], new ArrayList<Integer>());
			fdaTreeMap.get(data[10]).add(address);
		}		
	}
	
	public static void  insert2(int deleteId,String[] data) throws IOException{
		dbFile=new RandomAccessFile("PHARMA_TRIALS_1000B.db","rw");
		//write to the db file
		dbFile.seek(dbFile.length());
		int address=(int) dbFile.getFilePointer();;
		// write id
		int id=Integer.parseInt(data[0]);
		// write company
		dbFile.writeInt(id);
		int len=data[1].length();
		dbFile.writeByte(len);
		dbFile.writeBytes(data[1]);
		// write drug_id
		dbFile.writeBytes(data[2]);
		// write trails, patients and dosage_mg
		for(int i=3;i<6;i++){
			short s=Short.parseShort(data[i]);
			dbFile.writeShort(s);
		}
		// write reading
		float f=Float.parseFloat(data[6]);
		dbFile.writeFloat(f);	
		// write  double_blind,controlled_study,govt_funded and fda_approved
		Byte b=(byte) 00000000;
		
		// set the default delete single bit to 1 as delete flag

		if(id == deleteId){
			System.out.println("This ID has been flagged " + id); 

			b = (byte) (b | (0 << 7));
		}
		
		if(Boolean.parseBoolean(data[7]))
		b = (byte) (b | (1 << 3));			
		if (Boolean.parseBoolean(data[8]))	
		b = (byte) (b | (1 << 2));			
		if (Boolean.parseBoolean(data[9]))
		b = (byte) (b | (1 << 1));			
		if (Boolean.parseBoolean(data[10]))	
		b = (byte) (b | (1 << 0));
		dbFile.write(b);					
		if(idTreeMap.containsKey(id)){
			idTreeMap.get(id).add(address);
		}
		else{
			idTreeMap.put(id, new ArrayList<Integer>());
			idTreeMap.get(id).add(address);
		}
		if(compTreeMap.containsKey(data[1])){
			compTreeMap.get(data[1]).add(address);
		}
		else{
			compTreeMap.put(data[1],new ArrayList<Integer>());
			compTreeMap.get(data[1]).add(address);
		}
		if(drugTreeMap.containsKey(data[2])){
			drugTreeMap.get(data[2]).add(address);
		}
		else{
			drugTreeMap.put(data[2], new ArrayList<Integer>());
			drugTreeMap.get(data[2]).add(address);

		}
		short trials=Short.parseShort(data[3]);
		if(trialsTreeMap.containsKey(trials)){
			trialsTreeMap.get(trials).add(address);
		}
		else{
			trialsTreeMap.put(trials, new ArrayList<Integer>());
			trialsTreeMap.get(trials).add(address);
		}
		short patients=Short.parseShort(data[4]);
		if(patientsTreeMap.containsKey(patients)){
			patientsTreeMap.get(patients).add(address);
		}
		else{
			patientsTreeMap.put(patients, new ArrayList<Integer>());
			patientsTreeMap.get(patients).add(address);
		}
		short dosage=Short.parseShort(data[5]);
		if(dosageTreeMap.containsKey(dosage)){
			dosageTreeMap.get(dosage).add(address);
		}
		else{
			dosageTreeMap.put(dosage, new ArrayList<Integer>());
			dosageTreeMap.get(dosage).add(address);

		}
		float reading=Float.parseFloat(data[6]);
		if(readingTreeMap.containsKey(reading)){
			readingTreeMap.get(reading).add(address);
		}
		else{
			readingTreeMap.put(reading, new ArrayList<Integer>());
			readingTreeMap.get(reading).add(address);
		}
		if(doubleTreeMap.containsKey(data[7])){
			doubleTreeMap.get(data[7]).add(address);
		}
		else{
			doubleTreeMap.put(data[7], new ArrayList<Integer>());
			doubleTreeMap.get(data[7]).add(address);

		}
		if(controlledTreeMap.containsKey(data[8])){
			controlledTreeMap.get(data[8]).add(address);
		}
		else{
			controlledTreeMap.put(data[8], new ArrayList<Integer>());
			controlledTreeMap.get(data[8]).add(address);

		}
		if(govtTreeMap.containsKey(data[9])){
			govtTreeMap.get(data[9]).add(address);
		}
		else{
			govtTreeMap.put(data[9], new ArrayList<Integer>());
			govtTreeMap.get(data[9]).add(address);
		}
		if(fdaTreeMap.containsKey(data[10])){
			fdaTreeMap.get(data[10]).add(address);
		}
		else{
			fdaTreeMap.put(data[10], new ArrayList<Integer>());
			fdaTreeMap.get(data[10]).add(address);
		}		
	}
	
	private static void intGetAddress(int s, TreeMap<Integer,ArrayList<Integer>> map, int operator) throws IOException{
		 NavigableMap<Integer, ArrayList<Integer>> submap1=new TreeMap<Integer,ArrayList<Integer>>();
		 NavigableMap<Integer, ArrayList<Integer>> submap2=new TreeMap<Integer,ArrayList<Integer>>();
		switch(operator){
		//Returns a view of the portion of this map whose keys range from fromKey to toKey.
		case 1:submap1=map.subMap(s,false,map.lastKey(),true);
				for(Entry<Integer, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address=entry.getValue();
					readbyte(address);
				}
				break;
		case 2:submap1=map.subMap(s,true,map.lastKey(),true);
				for(Entry<Integer, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address=entry.getValue();
					readbyte(address);
				}
				break;
		case 3: ArrayList<Integer> address=map.get(s);
				  readbyte(address);
				  break;
		case 4:submap1=map.subMap(map.firstKey(),true,s,false);
				for(Entry<Integer, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				submap2=map.subMap(s,false,map.lastKey(),true);
				for(Entry<Integer, ArrayList<Integer>> entry:submap2.entrySet()){
					ArrayList<Integer> address2=entry.getValue();
					readbyte(address2);
				}
				break;
		case 5:submap1=map.subMap(map.firstKey(),true,s,true);
				for(Entry<Integer, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				break;
		case 6:submap1=map.subMap(map.firstKey(),true,s,false);
				for(Entry<Integer, ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				break;
		}
	}
	private static void readbyte(ArrayList<Integer> address) throws IOException{
		try{
		for(int i=0;i<address.size();i++){
			long add=address.get(i).longValue();
			dbFile.seek(add);
			int id=dbFile.readInt();
			int len=dbFile.read();
			byte[] company1=new byte[len];
			dbFile.read(company1);
			String company=new String(company1);
			byte[] drug_id1=new byte[6];
			dbFile.read(drug_id1);
			String drug_id=new String(drug_id1);
			short trials=dbFile.readShort();
			short patients=dbFile.readShort();
			short dosage=dbFile.readShort();
			float reading=dbFile.readFloat();
			String double_blind;
			String controlled_study;
			String govt_funded;
			String fda_approved;
			int b=dbFile.read();

				
			if ((b & 8)==8)
				double_blind = "true";
			else
				double_blind = "false";

			if ((b & 4)== 4)
				controlled_study = "true";
			else
				controlled_study = "false";

			if ((b & 2)== 2)
				govt_funded = "true";
			else
				govt_funded = "false";

			if ((b & 1)==1)
				fda_approved = "true";
			else
				fda_approved = "false";
			if ((b & 128)!=128){
				System.out.println("The data you are querying has been deleted from database.");
			}else{
				System.out.println(id+","+company+","+drug_id+","+trials+","+patients+","+dosage+","+reading+","+double_blind+","+controlled_study+","+govt_funded+","+fda_approved+",");

				}
			
			}
		}
		catch (IndexOutOfBoundsException e) {
		    System.err.println("IndexOutOfBoundsException: " + e.getMessage());
		} catch (IOException e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		}
	}
	private static void stringGetAddress(String s, TreeMap<String,ArrayList<Integer>> map, int operator) throws IOException{
		 NavigableMap<String, ArrayList<Integer>> submap1=new TreeMap<String,ArrayList<Integer>>();
		 NavigableMap<String, ArrayList<Integer>> submap2=new TreeMap<String,ArrayList<Integer>>();
		switch(operator){
		case 1:submap1=map.subMap(s,false,map.lastKey(),true);
				for(Map.Entry<String,ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address=entry.getValue();
					readbyte(address);
				}
				break;
		case 2:submap1=map.subMap(s,true,map.lastKey(),true);
				for(Map.Entry<String,ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address=entry.getValue();
					readbyte(address);
				}
				break;
		case 3: ArrayList<Integer> address=map.get(s);
				  readbyte(address);
				  break;
		case 4:submap1=map.subMap(map.firstKey(),true,s,false);
				for(Map.Entry<String,ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				submap2=map.subMap(s,false,map.lastKey(),true);
				for(Map.Entry<String,ArrayList<Integer>> entry:submap2.entrySet()){
					ArrayList<Integer> address2=entry.getValue();
					readbyte(address2);
				}
				break;
		case 5:submap1=map.subMap(map.firstKey(),true,s,true);
				for(Map.Entry<String,ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				break;
		case 6:submap1=map.subMap(map.firstKey(),true,s,false);
				for(Map.Entry<String,ArrayList<Integer>> entry:submap1.entrySet()){
					ArrayList<Integer> address1=entry.getValue();
					readbyte(address1);
				}
				break;
		}
	}
	private static void writeIntIndexFile(TreeMap<Integer,ArrayList<Integer>> map, File file) throws IOException	{
		FileWriter fw=new FileWriter(file);
		for(Map.Entry<Integer,ArrayList<Integer>> entry: map.entrySet()){
			Integer key=entry.getKey();
			ArrayList list=entry.getValue();
			fw.write(key+":");
			for(int i=0;i<list.size();i++){
				fw.write(list.get(i)+",");
			}
			fw.write("\n");
		}
		fw.close();
	}
	private static void writeStringIndexFile(TreeMap<String,ArrayList<Integer>> map, File file) throws IOException	{
		FileWriter fw=new FileWriter(file);
		for(Map.Entry<String,ArrayList<Integer>> entry: map.entrySet()){
			String key=entry.getKey();
			ArrayList list=entry.getValue();
			fw.write(key+":");
			for(int i=0;i<list.size();i++){
				fw.write(list.get(i)+",");
			}
			fw.write("\n");
		}
		fw.close();
	}
	private static void writeShortIndexFile(TreeMap<Short,ArrayList<Integer>> map, File file) throws IOException	{
		FileWriter fw=new FileWriter(file);
		for(Map.Entry<Short,ArrayList<Integer>> entry: map.entrySet()){
			Short key=entry.getKey();
			ArrayList list=entry.getValue();
			fw.write(key+":");
			for(int i=0;i<list.size();i++){
				fw.write(list.get(i)+",");
			}
			fw.write("\n");
		}
		fw.close();
	}
	private static void writeFloatIndexFile(TreeMap<Float,ArrayList<Integer>> map, File file) throws IOException	{
		FileWriter fw=new FileWriter(file);
		for(Map.Entry<Float,ArrayList<Integer>> entry: map.entrySet()){
			Float key=entry.getKey();
			ArrayList list=entry.getValue();
			fw.write(key+":");
			for(int i=0;i<list.size();i++){
				fw.write(list.get(i)+",");
			}
			fw.write("\n");
		}
		fw.close();
	}
}
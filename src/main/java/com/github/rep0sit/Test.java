package com.github.rep0sit;

import java.io.File;
import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import com.google.protobuf.ServiceException;

public class Test {
	private static TableName table1 = TableName.valueOf("Table1");
	private static String family1 = "Family1";
	private static String family2 = "Family2";
	
	
	private static Configuration getConf() {
		Configuration c = HBaseConfiguration.create();

		File file = new File("hbase-site.xml");
		java.nio.file.Path path = file.toPath();
		
		c.addResource(new Path(path.toString()));
		
		return c;
	}
	
	
	public static void main(String[] args) throws IOException{
		
		Connection connection = null;
		try {
			connection = ConnectionFactory.createConnection(getConf());
			HBaseAdmin.checkHBaseAvailable(getConf());
		} catch (IOException | ServiceException e) {e.printStackTrace();}
		
		
		Admin admin = connection.getAdmin();
		HTableDescriptor desc = new HTableDescriptor(table1);
		
		desc.addFamily(new HColumnDescriptor(family1));
		desc.addFamily(new HColumnDescriptor(family2));
		
		admin.createTable(desc);
	
	}
}

package com.github.rep0sit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class Table {
	
	
	private static final File FILE = new File("hbase-site.xml");
	private static final java.nio.file.Path PATH = FILE.toPath();
	private static Configuration conf = HBaseConfiguration.create();
	{
		conf.addResource(new Path(PATH.toString()));
	}
	
	
	private List<String> families = new ArrayList<>();
	private Connection connection = null;
	
	private HTableDescriptor desc = null;
	private TableName name = null;
	
	public Table(String name, List<String> families) {
		
		this.families = families;
		this.name = TableName.valueOf(name);
		desc = new HTableDescriptor(this.name);
		addFamilies();
		connect();
		Admin admin = null;
		try {
			admin = connection.getAdmin();
			admin.createTable(desc);
		} catch (IOException e) {e.printStackTrace();}
		
	}
	public Table(String name, String...families) {
		this(name, Arrays.asList(families));
	}

	private void connect() {
		
		try {
			connection = ConnectionFactory.createConnection(conf);
			HBaseAdmin.checkHBaseAvailable(conf);
		} catch (IOException | ServiceException e) {e.printStackTrace();}
		
	}
	
	private void addFamilies() {
		for(String family : families) {
			desc.addFamily(new HColumnDescriptor(family));
		}
	}
	
}

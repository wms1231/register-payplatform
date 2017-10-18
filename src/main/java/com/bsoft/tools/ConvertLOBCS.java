package com.bsoft.tools;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.sql.BLOB;
import oracle.sql.CLOB;
public class ConvertLOBCS {
    public static BLOB convertBlob
        ( BLOB inBlob, String sourcecs, String destcs ) 
        throws SQLException, IOException {
        Reader in = new InputStreamReader( 
           inBlob.getBinaryStream() , sourcecs ); 
        Connection conn = DriverManager.getConnection(
        		"jdbc:default:connection:") ;
        BLOB outBlob = BLOB.createTemporary( conn, true , BLOB.DURATION_CALL) ;
        Writer out = new OutputStreamWriter( 
           outBlob.getBinaryOutputStream() , destcs ); 
        int c;
        while ((c = in.read()) != -1)
           out.write(c);
        out.close();
        in.close();
        return outBlob ;
    }
    public static CLOB convertClob
        ( BLOB inBlob, String sourcecs ) 
        throws SQLException, IOException {
        Reader in = new InputStreamReader( 
           inBlob.getBinaryStream() , sourcecs ); 
        Connection conn = DriverManager.getConnection(
           "jdbc:default:connection:") ;
        CLOB outClob = CLOB.createTemporary( conn, true , CLOB.DURATION_CALL) ;
        Writer out = outClob.getCharacterOutputStream() ;
        // Writer out = outClob.getAsciiOutputStream() ;
        int c;
        while ((c = in.read()) != -1)
           out.write(c);
        out.close();
        in.close();
        return outClob ;
    }
    public static String convertStr
        ( BLOB inBlob, String sourcecs ) 
        throws SQLException, IOException {
        Reader in = new InputStreamReader( 
           inBlob.getBinaryStream() , sourcecs ); 
        StringBuffer outStr = new StringBuffer() ;
        int i, c ;
        i = 0 ;
        while ( ( (c = in.read()) != -1 ) & i < 1000 ) {
           outStr.append((char)c);
           i++ ;
        }
        in.close();
        return outStr.toString() ;
    }
}

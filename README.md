# Cassandra for storing metadata values in Treasure

## Steps for Local cassandra installation:
<ol>
  <li> Install Python 2.7 and add it to the path, before installing Cassandra </li>
  <li> Download & Install Cassandra from below link</li>
<code>https://www.apache.org/dyn/closer.lua/cassandra/3.11.10/apache-cassandra-3.11.10-bin.tar.gz </code>
or other versions from:
<code>https://cassandra.apache.org/download/ </code>

  <li> Install (Extract) Apache cassandra apache-cassandra-3.11.10-bin.tar.gz</li>
  <li> Run cassandra from the bin folder:
        <ul>
              <li>If you encounter a error during the startup:
              <code>
      INFO  [main] 2020-07-28 16:36:18,410 SigarLibrary.java:44 - Initializing SIGAR library
      \#
      \# A fatal error has been detected by the Java Runtime Environment:
      \#
      \#  EXCEPTION_ACCESS_VIOLATION (0xc0000005) at pc=0x0000000010014ed4, pid=19812, tid=0x000000000000481c
      \#
      \...
               </code>
              Open the cassandra/lib/sigar-bin/ folder and rename the sigar-amd64-winnt.dll to sigar-amd64-winntt.dll </li>
              <li> To run thrift: Open conf/cassandra.yml, search for start_rpc: false and change it to true </li>
              <li> 4.3 Restart cassandra </li>
        </ul>
  </li>
  <li> Open Cassandra CQL Shell to create Cassandra table & Schema </li>
  <li> Create Cassandra keyspace named "treasure"
<code>
CREATE KEYSPACE treasure WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

USE treasure;

CREATE TABLE media(
    file_uuid text PRIMARY KEY,
    metadata text
);
        </code>
      </li>
      <li> If you wish to import media data from a csv file, use the below query.
<code>
COPY MEDIA (file_uuid, metadata) FROM 'c:\temp\media_202107011456.csv' WITH DELIMITER=',' AND HEADER=true AND CHUNKSIZE=1;
</code>
This may sometimes throw an error. In such a case check the below fix:

<code>
> Failed to import 1 rows: Error - field larger than field limit (131072),  given up after 1 attempts
> You will find cqlshrc.sample file in conf directory after you extracted
> Copy the cqlshrc.sample to ~/.cassandra and rename it to cqlshrc
> Open the cqlshrc file and change ; field_size_limit = 131072 to field_size_limit = 1000000000
> Don't forget to remove ";" in the above step
> Open a new terminal & run your queries

Open the file **C:\Users\raman\.cassandra\cqlshrc ** from your home folder
updated the following with

[csv]
;; The size limit for parsed fields
field_size_limit = 99999999
</code>
<li>Run the cqlsh again and try.
<code>
C:\> cqlsh --cqlshrc=c:\Users\raman\.cassandra\cqlshrc -C --keyspace=treasure
</code>
** Note:
    drop table media;
    and create media table again 
    Then reimport the csv. **
  </li>
<ol>
<hr/>

## Steps for Cassandra on docker:

<code> docker pull cassandra</code>

<code>docker run --name treasure -v /c/apache-cassandra-3.11.10/data/data:/var/lib/cassandra/data  -it -d cassandra </code>

<code>/c/apache-cassandra-3.11.10/data/data --> Already has all the keyspaces from windows</code>

<code> docker run -p 9042:9042 --name treasure -v /c/apache-cassandra-3.11.10/data/data:/var/lib/cassandra/data  -it -d cassandra<code>

Test the urls to ensure that both the datasources are working:

http://localhost:8080/getMetadata/712ddc5d-fcc1-11ea-ae5f-3b80cfa1eb9a  --> Non-existant

http://localhost:8080/getMetadata/0285b5c0-f76b-11ea-931c-1b3146828a15 --> Working

http://localhost:8080/getT1/1

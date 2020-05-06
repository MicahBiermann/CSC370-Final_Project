 /*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//Adapted by Micah Biermann
package csc370FinalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import static csc370FinalProject.Csc370FinalProject.INDEX_DIR;
import static csc370FinalProject.Csc370FinalProject.FILEPATH;

public class LuceneIndexer 
{
    /** Index all text files under a directory. */
    public static void indexer(String indexPath, String[] docPaths, String[] docNames)
    {        
        boolean create = true;
        for (int i = 0; i < docPaths.length; i++)
        {
            if(i > 0)
            {
                create = false;
            }
            Path docDir = Paths.get(docPaths[i]);
            if (!Files.isReadable(docDir))
            {
                System.out.println("Document directory '" +docDir.toAbsolutePath()+ "' does not exist or is not readable, please check the path");
                System.exit(1);
            }

            Date start = new Date();
            try
            {
                //System.out.println("Indexing to directory '" + indexPath + "'...");

                Directory dir = FSDirectory.open(Paths.get(indexPath));
                Analyzer analyzer = new StandardAnalyzer();
                IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

                if (create)
                {
                    // Create a new index in the directory, removing any
                    // previously indexed documents:
                    iwc.setOpenMode(OpenMode.CREATE);
                }
                else
                {
                    // Add new documents to an existing index:
                    iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
                }

                IndexWriter writer = new IndexWriter(dir, iwc);
                indexDoc(writer, docDir, Files.getLastModifiedTime(docDir).toMillis(), docNames[i]);
                // NOTE: if you want to maximize search performance,
                // you can optionally call forceMerge here.  This can be
                // a terribly costly operation, so generally it's only
                // worth it when your index is relatively static (ie
                // you're done adding documents to it):
                // writer.forceMerge(1);

                writer.close();
                Date end = new Date();
                //System.out.println(end.getTime() - start.getTime() + " total milliseconds");

            }
            catch (IOException e)
            {
                System.out.println("An error occured. Please check inputs and try again. Error:" + e);
            }
        }
    }

    /** Indexes a single document */
    private static void indexDoc(IndexWriter writer, Path file, long lastModified, String name) throws IOException
    {
        try (InputStream stream = Files.newInputStream(file))
        {
            // make a new, empty document
            Document doc = new Document();

            // Add the path of the file as a field named "path".  Use a
            // field that is indexed (i.e. searchable), but don't tokenize 
            // the field into separate words and don't index term frequency
            // or positional information:
            Field pathField = new StringField("path", file.toString(), Field.Store.YES);
            doc.add(pathField);
            
            // Add a title field based upon the user input title
            // Managed in the same way as the path field
            Field titleField = new StringField("title", name, Field.Store.YES);
            doc.add(titleField);
            
            // Add the last modified date of the file a field named "modified".
            // Use a LongPoint that is indexed (i.e. efficiently filterable with
            // PointRangeQuery).  This indexes to milli-second resolution, which
            // is often too fine.  You could instead create a number based on
            // year/month/day/hour/minutes/seconds, down the resolution you require.
            // For example the long value 2011021714 would mean
            // February 17, 2011, 2-3 PM.
            doc.add(new LongPoint("modified", lastModified));

            // Add the contents of the file to a field named "contents".  Specify a Reader,
            // so that the text of the file is tokenized and indexed, but not stored.
            // Note that FileReader expects the file to be in UTF-8 encoding.
            // If that's not the case searching for special characters will fail.
            doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));
            
            if (writer.getConfig().getOpenMode() == OpenMode.CREATE)
            {
              // New index, so we just add the document (no old document can be there):
              //System.out.println("Adding " + file);
              writer.addDocument(doc);
            }
            else
            {
                // Existing index (an old copy of this document may have been indexed) so 
                // we use updateDocument instead to replace the old one matching the exact 
                // path, if present:
                //System.out.println("Updating " + file);
                writer.updateDocument(new Term("path", file.toString()), doc);
            }
        }
    }
}

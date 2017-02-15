<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>Query Results</title>
</head>

<body>
    <h1>Query Results</h1>
    
    <p>
        <?php
            $album = filter_input(INPUT_GET, "albumName");
            $singer  = filter_input(INPUT_GET, "singerName");
            $gender  = filter_input(INPUT_GET, "gender");
            $year = filter_input(INPUT_GET,"year");
            $styles = isset($_GET["style"])?$_GET["style"]:array();
            $sCount = count($styles);

            //echo $sCount;
            //echo $style[0],$style[1];
            
            try {
                // Connect to the database.
                $con = new PDO("mysql:host=localhost;dbname=fancycoders",
                               "fancycoders", "sesame");
                $con->setAttribute(PDO::ATTR_ERRMODE,
                                   PDO::ERRMODE_EXCEPTION);
                
                //construct the query
                $query = "SELECT * FROM album WHERE ";
                if ((strlen($album) > 0)) {
                    $query = $query. "name = '$album' AND   ";
                }
                if((strlen($singer)>0)){
                    $query = $query. "singer = '$singer' AND   ";
                }
                if((strlen($gender)>0)){
                    $query = $query. "gender = '$gender' AND   ";
                }
                if((strlen($year)>0)){
                    $query = $query. "year = '$year' AND   ";
                }
                if((count($styles)>0)){
                    $query = $query. "(";
                    foreach ($styles as &$s){
                        $query = $query. "style = '$s' OR    ";
                    }
                    $query = substr($query,0,-6).")";
                }else{
                    $query = substr($query, 0,-6);
                }

                echo $query;

               

                // // We're going to construct an HTML table.
                print "<table border='1'>\n";
                
                // Query the database.
                $data = $con->query($query);
                $data->setFetchMode(PDO::FETCH_ASSOC);

                $doHeader = true;
                //echo count($data);
                
                foreach ($data as $row) {
                                    
                    // The header row before the first data row.
                    if ($doHeader) {
                        print "        <tr>\n";
                        foreach ($row as $name => $value) {
                            print "            <th>$name</th>\n";
                        }
                        print "        </tr>\n";
                        
                        $doHeader = false;
                    }
                    
                    // Data row.
                    print "            <tr>\n";
                    foreach ($row as $name => $value) {
                        print "                <td>$value</td>\n";
                    }
                    print "            </tr>\n";
                }
                
                
                print "        </table>\n";
            }
            catch(PDOException $ex) {
                echo 'ERROR: '.$ex->getMessage();
            }        
        ?>
    </p>
</body>
</html>
<!DOCTYPE html>
<head>
        <title>Volunteer List</title>
        <link rel="stylesheet" type="text/css" href="list_style.css"/>
	<script type="text/javascript">     
    
              function  printPage(){
	          window.print();
       	}
       </script>
</head>
<html>
<body>
     <h1>List of Volunteers</h1>
     <button onclick="printPage()">Print</button>
       <?php
        if (file_exists('volunteers.json'))
        {
            $data = file_get_contents('volunteers.json');
            $volunteers = json_decode($data);
            if(empty($volunteers))
            {
                  echo "<h1>There are no volunteers.</h1>";
            }
            else
            {
	         echo '<div id="divToPrint">';
                 echo   '<table>';
                 echo   '<tr>'; 
                 echo   '<th>First Name</th>';
                 echo   '<th>Last Name</th>';
                 echo   '<th>Email</th>';
                 echo   '<th>Phone No.</th>';
                 echo   '</tr>';
                    foreach ($volunteers as $v)
                    {
                        echo '<tr>';
                        echo  '<td>'.$v->fname.'</td>';
                        echo  '<td>'.$v->lname.'</td>';
                        echo  '<td>'.$v->email.'</td>';
                        echo  '<td>'.$v->phone.'</td>';
                        echo '</tr>';
                    }
                 echo  '</table>';
                 echo '</div>'; 
            }
        }
        else
        {
            echo "<h1>File not found</h1>";
        }
       ?>
</body>       
</html>

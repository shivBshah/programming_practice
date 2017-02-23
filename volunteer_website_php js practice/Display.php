<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" type="text/css" href="list.css">
    </head>
    
    <body>

    <h1>List of Volunteers</h1>
    <?php
    $data = file_get_contents("volunteers.json");
    $json = json_decode($data, true); 
    ?>    
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email Address</th>
            <th>Phone Number</th>
        </tr>
    
      <?php    
        foreach ($json as $d) {
         echo "<tr>";
         echo "<td>".$d["first"]."</td>";
         echo "<td>".$d["last"]."</td>";
         echo "<td>".$d["email"]."</td>";
         echo "<td>".$d["phone"]."</td>";
         echo "</tr>";
       }          
      ?>    
    </table>
    <button onclick="myprint()">Print</button>
        
    <script>
        function myprint() {
            window.print();
        }    
    </script>
    </body>
</html>

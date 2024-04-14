<?php
include("connect.php");

// SQL query to fetch employee sales data
$sql = "SELECT ename, SUM(transaction.quantity) AS total_quantity, SUM(transaction.quantity * product.price) AS total_price FROM transaction INNER JOIN product ON transaction.pname = product.name GROUP BY ename";

// Execute the query
$result = $conn->query($sql);

// Set headers for download
header('Content-Type: text/csv');
header('Content-Disposition: attachment; filename="employee_sales.csv"');

// Output table data as CSV
$output = fopen('php://output', 'w');
fputcsv($output, array('Employee Name', 'Total Quantity Sold', 'Total Sales Amount'));

// Fetch data and output to CSV
while ($row = $result->fetch_assoc()) {
    fputcsv($output, array($row['ename'], $row['total_quantity'], $row['total_price']));
}

// Close file pointer
fclose($output);

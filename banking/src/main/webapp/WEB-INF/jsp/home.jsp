<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction Actions</title>
</head>
<body>
    <h1>Banking Application</h1>

    <!-- Form for Make Transaction API -->
    <form action="/api/transaction/transfer" method="post">
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" required><br><br>

        <label for="receiverAccNumber">Receiver Account Number:</label>
        <input type="number" id="receiverAccNumber" name="receiverAccNumber" required><br><br>

        <button type="submit">Make Transaction</button>
    </form>
    <br><br>

    <!-- Form for Fetch All Users API -->
    <form action="/api/admin/getUser" method="get">
        <button type="submit">Fetch All Users</button>
    </form>
</body>
</html>

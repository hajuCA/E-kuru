<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-kuru</title>
    <link rel="shortcut icon" href="../resources/img/HatchfulExport-All/ekuru_logo.ico">
    <link rel="stylesheet" href="../resources/css/request-main.css">
    <link rel="stylesheet" href="../resources/font/NotoSansCJKjp-Black.otf">
    <link rel="stylesheet" href="../resources/css/bootstrap-4.6.0-dist/css/bootstrap-grid.css">
    <link rel="stylesheet" href="../resources/css/bootstrap-4.6.0-dist/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="../resources/css/bootstrap-4.6.0-dist/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="../resources/css/bootstrap-4.6.0-dist/css/bootstrap-reboot.min.css">
    <link rel="stylesheet" href="../resources/css/bootstrap-4.6.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/bootstrap-4.6.0-dist/css/bootstrap.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        * {
            font-family: 'NotSansCJKjp-Black', sans-serif;
        }

        body {
            background-color: #FFDFB9;
        }

        .st {
            width: 50% !important;
            margin-top: 10%;
        }

        footer{
            width: 100% !important;
        }
    </style>
</head>

<body>
    <%@ include file="/WEB-INF/views/header.jsp" %>
    <!--마이 포인트 start-->
    <div class="container" style="margin-top: 10%;">
        <!--현재 잔여 포인트-->
        <div class="card text-center">
            <div class="card-header">
                <h2>My point</h2>
                <h1 id="h1-p">5000P</h1>
            </div>
            <div class="card-body">
                <!--충전 및 환불 버튼 부분-->
                <div>
                    <div class="row g-2 ">
                        <div class="col-6" style="margin-left: auto; margin-right: auto;">
                            <button type="button" class="btn btn-outline-danger"
                                style="margin-right: 5%;">Refund</button>
                            <button type="button" class="btn btn-outline-danger">Charge</button>
                        </div>
                    </div>

                </div>
                <!-- 충전 내역 출력 부분 -->
                <div>
                    <form action="#">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Charged Point</th>
                                    <th scope="col">Date</th>
                                    <th scope="col">Credit/Cash</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>1000P</td>
                                    <td>2021-02-19</td>
                                    <td>Cash</td>
                                </tr>
                                <tr>
                                    <th scope="row">2</th>
                                    <td>500p</td>
                                    <td>2021-02-20</td>
                                    <td>Credit</td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div class="card-footer text-muted">
                    Make your point be full, you will be happy
                </div>
            </div>
        </div>
        <!--마이 포인트 end-->
        <!-- include tag Footer Start -->
    </div>
    <div>
<%@ include file="/WEB-INF/views/main-footer.jsp" %>
</body>
</html>
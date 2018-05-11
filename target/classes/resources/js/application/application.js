//var Application = (function (Application) {

        // $(document).on("click", ".login__button", function () {
        //     var username = $("#account-username").val();
        //     var password = $("#account-password").val();
        //     $.ajax({
        //         url: "login.html",
        //         type: "GET",
        //         headers: {
        //             "Authorization": "Basic " + btoa(username + ":" + password)
        //         },
        //         success: function (response, status) {
        //             $(".user__img").addClass("d-none");
        //             console.log(response);
        //             var logoutButoon = $("<a>").addClass("btn btn-outline-danger")
        //                 .attr("href", "logout.html").html("Logout")
        //
        //             $(".user__img").remove();
        //             $(".user-collapse").remove();
        //             $(".loginSearch__container").append(logoutButoon);
        //         },
        //         error: function (error) {
        //             console.log("ERROR");
        //         }
        //
        //     })
//})


// function getLoansFromServer() {
//     fetch('http://localhost:3000/loans')
//         .then(function (response) {
//             response.json().then(function (loans) {
//                 renderLoans(loans);
//             });
//         });
// };
        const main = document.getElementById('main');

        function createLoanDOMNode() {

            var divDataGrid = document.createElement('div');
            div.className = "datagrid";

            var table = document.createElement('table');
            var thead = document.createElement('thead');
            var headrow = document.createElement('tr');

            var thUser = document.createElement('th');
            thUser.textContent = "USER";
            var thDate = document.createElement('th');
            thDate.textContent = "DATE";
            var thBook = document.createElement('th');
            thBook.textContent = "BOOK";

            headrow.appendChild(thUser);
            headrow.appendChild(trBook);
            headrow.appendChild(trDate);

            thead.appendChild(headrow);
            table.appendChild(thead);

            var tfoot = document.createElement('tfoot');


            var tbody = document.createElement('tbody');
            var row = document.createElement('tr');

            return table;
        }

        function renderLoans() {



            // Create and append tags
            // for (var i = 0; i < articles.length; i++) {
               var articleDOMNode = createLoanDOMNode();
            main.appendChild(articleDOMNode);
            //}
        }

        renderLoans();
//    })
//(Application || {})
//)
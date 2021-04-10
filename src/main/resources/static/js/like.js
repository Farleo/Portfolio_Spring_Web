
function like(id) {
    var data = {
        id : id
    };
    $.ajax({
        type: "GET",
        url: "/blog/like/",
        data: data,
        dataType: 'json',

        success: function (result) {
            show(id);
            console.log("Liked: ", result);
            if(result===true){
                document.getElementById('error_message').style.display = "block";
            }

        },
        error: function (e) {
            console.log("ERROR Like: ", e);
        }
    });
}
function show(id) {
    var data = {
        id : id
    };
    $.ajax({
        type: "GET",
        url: "/blog/like_show/",
        data: data,
        dataType: 'json',

        success: function (result) {
            $('#getResultDiv div').html(result);
            console.log("Change like: ", result);
            document.getElementById('like-count-text').innerHTML = result;
            $( "#error_message" ).fadeOut( 6000, "linear", result );
        },
        error: function (e) {
            $("#getResultDiv").html("<strong>Error</strong>");
            console.log("ERROR: ", e);
        }
    });
}
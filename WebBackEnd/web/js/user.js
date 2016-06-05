/**
 * Created by lyn on 16-6-1.
 */

function register()
{
    var regForm=$("#regForm");
    var formData=regForm.serializeObject();
    if(formData.password1.toString() != formData.password2.toString())
        msg("Please confirm password!");
    else if(formData.role==undefined)
        msg("Please choose role!");
    else
    {
        ajax("User","get",
            {
                action: "register",
                userData: JSON.stringify({
                    username: formData.username,
                    company: formData.company,
                    password: formData.password1,
                    role:formData.role
                })
            },function (data) {
                //alert(data);

            });

    }
    return false;
    
}
function login() {
    var loginForm=$("#loginForm");
    var formData=loginForm.serializeObject();
    formData.action="login";
    ajax("User","get",formData,function (data) {
        setCookie("username",formData.username,"d1","/");

    });
    return false;
}

function showLoginInfo(username) {
    if(username!="" && username!=null)
    {
        $(".notLogin").hide();
        $("#username").html("Hi! "+username);
        $(".Logined").show();


    }

}

function logout() {
    setCookie("username","","s0","/");
    
}
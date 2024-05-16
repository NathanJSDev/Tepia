// // OnLoad method
// window.onload = function () {
//     var gsi = {};
//     // verify if has an existent session on this browser
//     if (sessionStorage.getItem("_k") != (null || undefined)) {
//         console.log("already exists");
//     } else {
//         let gsiHs = new Headers(); gsiHs.append("Content-Type", "application/json");
//         console.log("passes here");
//         fetch(location + "/gnsi", {
//             method: "GET",
//             headers: gsiHs,
//             redirect: 'follow'
//         }).then((response) => {response.text(); console.log(response);})
//         .then((result) => {console.log(result); sessionStorage.setItem("_k", JSON.parse(result))})
//         .catch((error) => console.error(error));
//     }
// }


function vote(id, value) {
    var vp = true, vce = false, vc, headers = new Headers();
    headers.append('Content-Type', 'application/json');

    if (localStorage.getItem("voted") != (null || undefined)) {
        vce = true;
        vc = JSON.parse(localStorage.getItem("voted"));
        vc.vc.forEach(ci=>{if (id == ci) vp = false;});
    }

    if (vp == true) {
        if (vce == true) {
            vc.vc[vc.vc.length] = id;
            localStorage.setItem("voted", JSON.stringify(vc));
        } else {
            vc = { "vc": [id] };
            localStorage.setItem("voted", JSON.stringify(vc));
        }

        fetch(window.location + '/afs', {
            method: 'POST',
            headers: headers,
            redirect: 'follow',
            body: JSON.stringify({
                'id': id,
                'value': value,
                'sk': "any-key"
            })
        }).catch((reason) => console.error(reason));

    }

    setTimeout(window.location.reload(), 30);
}


function toggleDropper(el) {
    let dropper = el.parentNode;
    let targetNode = dropper.childNodes[3];

    targetNode.classList.toggle("dn");
}


var comment_options_elements = document.getElementsByClassName("o-dropper-items");
var comment_options_dropper_ic = document.getElementsByClassName("cod-ic");

var about_the_app_modal = document.getElementById("about");
// When the user clicks anywhere outside of the dropper or the modal, close it
window.onclick = function (event) {
    for (i = 0; i < comment_options_elements.length; i++) {
        if (event.target == comment_options_dropper_ic[i]) {
            comment_options_elements[i].classList.toggle("dn");
        }
        else if (event.target != comment_options_elements[i] && !comment_options_elements[i].classList.contains("dn")) {
            comment_options_elements[i].classList.add("dn");
        }
    }


}

function ata(){
    let ataEl = document.getElementById("ata");
    ataEl.classList.toggle("dn");
}
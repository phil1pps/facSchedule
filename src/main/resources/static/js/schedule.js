/*

let specId = document.querySelector('.hidden-spec-id').value;

let Http2 = new XMLHttpRequest();
let url2=`http://localhost:8080/schedule/getSpecialitySubjects/${specId}`;
Http2.open("GET", url2);
Http2.responseType = 'json';
Http2.send();
let classes = [];
Http2.onreadystatechange = (e) => {
    if(Http2.readyState === 4 && Http2.status === 200) {
        console.log(Http2.response);
        classes = Http2.response;
        console.log(classes);
    }
}*/


/*

if (window.sessionStorage.token) { xhr.setRequestHeader("Authorization", $window.sessionStorage.token); }
*/

let Http = new XMLHttpRequest();
let url='http://localhost:8080/deanery/getSpecialities';
Http.open("GET", url);
Http.responseType = 'json';
Http.send();
Http.onreadystatechange = (e) => {
    if(Http.readyState === 4 && Http.status === 200) {
        initSpecialities(Http.response);
    }
}

let Http3 = new XMLHttpRequest();
let url3='http://localhost:8080/deanery/getAllProfessors';
Http3.open("GET", url3);
Http3.responseType = 'json';
Http3.send();
Http3.onreadystatechange = (e) => {
    if(Http3.readyState === 4 && Http3.status === 200) {
        console.log(Http3.response);
        initProfessors(Http3.response);
    }
}

let Http4 = new XMLHttpRequest();
let url4='http://localhost:8080/deanery/getAllProfessors';
Http4.open("GET", url4);
Http4.responseType = 'json';
Http4.send();
Http4.onreadystatechange = (e) => {
    if(Http4.readyState === 4 && Http4.status === 200) {
        console.log(Http4.response);
        initProfessors(Http4.response);
    }
}


function initSpecialities(jsonArray) {
    let dataListSpecialities = document.querySelectorAll(".specialities");
   jsonArray.forEach((dataItem) => {
       dataListSpecialities.forEach((domItem) => {
           if(Object.prototype.hasOwnProperty.call(dataItem, 'idSpeciality') && Object.prototype.hasOwnProperty.call(dataItem, 'specialityName'))
               domItem.innerHTML +=  `<option value="${dataItem.idSpeciality}"> ${dataItem.specialityName}</option>`;
       });
   });


    initializeSubject();
}

function initProfessors(jsonArray) {
    let dataListProfessors = document.querySelectorAll(".professor-list");
    jsonArray.forEach((dataItem) => {
        dataListProfessors.forEach((domItem) => {
            if(Object.prototype.hasOwnProperty.call(dataItem, 'professorName') && Object.prototype.hasOwnProperty.call(dataItem, 'idProfessor'))
                domItem.innerHTML +=  `<option value="${dataItem.idProfessor}"> ${dataItem.professorName}</option>`;
        });
    });
}

let selectPickSpecForSubjectShow = document.querySelector('.specialities-pick-group');

function initSubjects(jsonArray) {
    let dataListSubjects = document.querySelectorAll(".subjects-list");
    jsonArray.forEach((dataItem) => {
        dataListSubjects.forEach((domItem) => {
            if( Object.prototype.hasOwnProperty.call(dataItem, 'idSubject')
                && Object.prototype.hasOwnProperty.call(dataItem, 'subjectName')
                && Object.prototype.hasOwnProperty.call(dataItem, 'course')
            )
                domItem.innerHTML +=  `<option value="${dataItem.idSubject}"> ${dataItem.subjectName} (${dataItem.course})</option>`;
        });
    });
}


function initializeSubject() {
    let Http2 = new XMLHttpRequest();
    let url2=`http://localhost:8080/deanery/getSpecialitySubjects/${selectPickSpecForSubjectShow.value}`;
    Http2.open("GET", url2);
    Http2.responseType = 'json';
    Http2.send();
    Http2.onreadystatechange = (e) => {
        if(Http2.readyState === 4 && Http2.status === 200) {
            initSubjects(Http2.response);
        }
    }
}
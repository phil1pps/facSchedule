

if (window.sessionStorage.token) { xhr.setRequestHeader("Authorization", $window.sessionStorage.token); }

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
let url3='http://localhost:8080/deanery/getProfessors';
Http3.open("GET", url3);
Http3.responseType = 'json';
Http3.send();
Http3.onreadystatechange = (e) => {
    if(Http3.readyState === 4 && Http3.status === 200) {
        console.log(Http3.response);
        initProfessors(Http3.response);
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



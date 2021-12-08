let buttonSubmitForAddSubjects = document.querySelector('.button-form-submit-subject');

buttonSubmitForAddSubjects.addEventListener('click', function(){

    let json = `
    {
        "subjectName":"${document.querySelector(".name-input").value}",
        "course":"${document.querySelector(".course-input").value}"
    }
        `;
    let url = `http://localhost:8080/deanery/addSubject/${document.querySelector("#specialities").value}`;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(json);
    xhr.onreadystatechange = (e) => {
        if (xhr.readyState === 4 && (xhr.status === 200 || xhr.status === 400)) {
            console.log(xhr.responseText);

            console.log(document.querySelector("#specialities").value);
            console.log(json);
        }
    }
});

let buttonSubmitForAddProfessor = document.querySelector('.button-form-submit-professor');

buttonSubmitForAddProfessor.addEventListener('click', function(){

    let json = `
    {
        "professorName":"${document.querySelector(".professor-name-input").value}",
        "login":"${document.querySelector(".professor-login-input").value}",
        "password":"${document.querySelector(".professor-password-input").value}"
    }
        `;
    let url = `http://localhost:8080/deanery/registerProfessor`;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(json);
    xhr.onreadystatechange = (e) => {
        if (xhr.readyState === 4 && (xhr.status === 200 || xhr.status === 400)) {
            console.log(xhr.responseText);
            console.log(json);
        }
    }
});

let buttonSubmitForAddStudent = document.querySelector('.button-form-submit-student');

buttonSubmitForAddStudent.addEventListener('click', function(){

    let json = `
    {
        "studentName":"${document.querySelector(".student-name-input").value}",
        "yearOfAdmission":"0",
        "login":"${document.querySelector(".student-login-input").value}",
        "password":"${document.querySelector(".student-password-input").value}"
    }
        `;
    let url = `http://localhost:8080/deanery/registerStudent${document.querySelector("#student-speciality").value}`;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(json);
    xhr.onreadystatechange = (e) => {
        if (xhr.readyState === 4 && (xhr.status === 200 || xhr.status === 400)) {
            console.log(xhr.responseText);
            console.log(json);
        }
    }
});


let buttonSubmitForAddGroup = document.querySelector('.button-form-submit-group');

buttonSubmitForAddGroup.addEventListener('click', function(){

    let json = `
    {
        "groupName":"${document.querySelector(".group-name-input").value}"
    }
        `;
    let url = `http://localhost:8080/deanery/addGroupToSubject?idSubject=${document.querySelector(".subjects-list").value}&idProfessor=${document.querySelector(".professor-list").value}`;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(json);
    xhr.onreadystatechange = (e) => {
        if (xhr.readyState === 4 && (xhr.status === 200 || xhr.status === 400)) {
            console.log(xhr.responseText);
            console.log(json);
        }
    }
});



let selectPickSpecForSubjectShow = document.querySelector('.specialities-pick-group');

selectPickSpecForSubjectShow.addEventListener('change', (event) => {
    //create query for subj by spec id

    let Http2 = new XMLHttpRequest();
    let url2=`http://localhost:8080/deanery/getSubjects/${selectPickSpecForSubjectShow.value}`;
    Http2.open("GET", url2);
    Http2.responseType = 'json';
    Http2.send();
    Http2.onreadystatechange = (e) => {
        if(Http2.readyState === 4 && Http2.status === 200) {
            initSubjects(Http2.response);
        }
    }

});

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

function show() {

}
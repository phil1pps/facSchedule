function setCrud(entityType, id) {
    switch (entityType) {
        case 'speciality':
            id = id.substr(11);
            let currentForm = document.querySelector(`.speciality-edit`);
            currentForm.style.display = 'block';
            let notCurrentForm = document.querySelector(`.subject-edit`);
            notCurrentForm.style.display = 'none';
            let subButton =  document.querySelector(`.button-form-submit-speciality-edit`);
            let inputName = document.querySelector('.speciality-name-input');

            let Http = new XMLHttpRequest();
            let url='http://localhost:8080/deanery/getSpecialities';
            Http.open("GET", url);
            Http.responseType = 'json';
            Http.send();
            let specialities = [];
            Http.onreadystatechange = (e) => {
                if(Http.readyState === 4 && Http.status === 200) {
                    console.log(Http.response);
                    specialities = Http.response;
                    process();
                }
            }
            function process() {
                specialities.forEach((speciality) => {
                    if(Object.prototype.hasOwnProperty.call(speciality, 'idSpeciality') && Object.prototype.hasOwnProperty.call(speciality, 'specialityName')) {
                        if(speciality.idSpeciality - id === 0)
                            inputName.value = speciality.specialityName;
                    }
                });
            }
            subButton.addEventListener('click', function () {
                let json = `
                {
                    "specialityName":"${document.querySelector(".speciality-name-input").value}"
                }`;
                let Http2 = new XMLHttpRequest();
                let url2=`http://localhost:8080/deanery/editSpeciality/${id}`;
                Http2.open("POST", url2);
                Http2.responseType = 'json';
                Http2.setRequestHeader('Content-Type', 'application/json');
                Http2.send(json);
                Http2.onreadystatechange = (e) => {
                    if(Http2.readyState === 4 && Http2.status === 200) {
                        console.log(Http2.response);
                        process();
                    }
                }
            });
            break;
       /* case 'subject':
            id = id.substr(11);
            let currentForm1 = document.querySelector(`.speciality-edit`);
            currentForm1.style.display = 'block';
            let notCurrentForm1 = document.querySelector(`.subject-edit`);
            notCurrentForm1.style.display = 'none';
            let subButton1 =  document.querySelector(`.button-form-submit-speciality-edit`);
            let inputName1 = document.querySelector('.speciality-name-input');

            let Http1 = new XMLHttpRequest();
            let url1='http://localhost:8080/deanery/getSpecialities';
            Http1.open("GET", url1);
            Http1.responseType = 'json';
            Http1.send();
            let subjects = [];
            Http1.onreadystatechange = (e) => {
                if(Http1.readyState === 4 && Http1.status === 200) {
                    console.log(Http1.response);
                    subjects = Http1.response;
                    process1();
                }
            }
        function process1() {
            subjects.forEach((subject) => {
                if(Object.prototype.hasOwnProperty.call(speciality, 'idSpeciality') && Object.prototype.hasOwnProperty.call(speciality, 'specialityName')) {
                    if(speciality.idSpeciality === id)
                        inputName.value = speciality.specialityName;
                }
            });
        }
            subButton.addEventListener('click', function () {
                let json = `
                {
                    "specialityName":"${document.querySelector(".speciality-name-input").value}"
                }`;
                let Http = new XMLHttpRequest();
                let url=`http://localhost:8080/deanery/editSpeciality/${id}}`;
                Http.open("POST", url);
                Http.responseType = 'json';
                Http.send();
                let specialities = [];
                Http.onreadystatechange = (e) => {
                    if(Http.readyState === 4 && Http.status === 200) {
                        console.log(Http.response);
                        specialities = Http.response;
                        process();
                    }
                }
            });*/
    }
}

function highlightClickedSpecialityOrProfessor(domClicked, isProfessor) {
    let pastDomHighlighted = document.querySelector('.choosed-list-item-clicked-speciality');
    if (pastDomHighlighted !== null && pastDomHighlighted!== undefined) {
        pastDomHighlighted.classList.remove('choosed-list-item-clicked-speciality');
    }
    domClicked.classList.add('choosed-list-item-clicked-speciality');

    if (isProfessor) {
        let pastDomHighlighted = document.querySelector('.choosed-list-item-clicked-student');
        if (pastDomHighlighted !== null && pastDomHighlighted!== undefined) {
            pastDomHighlighted.classList.remove('choosed-list-item-clicked-student');
        }
    }
}
function highlightClickedStudentOrSubject(domClicked) {
    let pastDomHighlighted = document.querySelector('.choosed-list-item-clicked-student');
    if (pastDomHighlighted !== null && pastDomHighlighted!== undefined) {
        pastDomHighlighted.classList.remove('choosed-list-item-clicked-student');
    }
    domClicked.classList.add('choosed-list-item-clicked-student');
}

function init() {
    let Http = new XMLHttpRequest();
    let url='http://localhost:8080/deanery/getSpecialities';
    Http.open("GET", url);
    Http.responseType = 'json';
    Http.send();
    let specialities = [];
    Http.onreadystatechange = (e) => {
        if(Http.readyState === 4 && Http.status === 200) {
            console.log(Http.response);
           specialities = Http.response;
            processSpeciality();

            initializeSubject();
        }
    }

    //todo
    let Http2 = new XMLHttpRequest();
    let url2=`http://localhost:8080/deanery/getAllProfessors`;
    Http2.open("GET", url2);
    Http2.responseType = 'json';
    Http2.send();
    let professors = [];
    Http2.onreadystatechange = (e) => {
        if(Http2.readyState === 4 && Http2.status === 200) {
            console.log(Http2.response);
            professors = Http2.response;
            processProfessors();
        }
    }

    function processSpeciality() {

        let specialitiesUl = document.querySelector('.specialities-ul');
        specialitiesUl.innerHTML = '';

        specialities.forEach((speciality) => {
            if(Object.prototype.hasOwnProperty.call(speciality, 'idSpeciality') && Object.prototype.hasOwnProperty.call(speciality, 'specialityName')) {
                let specDOM = document.createElement('li');
                specDOM.classList.add('speciality-list-item');
                specDOM.id = `speciality-${speciality.idSpeciality}`;
                specDOM.innerHTML = `  ${speciality.specialityName}`;
                specialitiesUl.append(specDOM);

                //adding listener for speciality
                specDOM.addEventListener('click',function () {
                    if (!specDOM.classList.contains('clicked')) {
                        showStudentsAndSubjectsForSpeciality(specDOM.id);
                        specDOM.classList.add('clicked');
                    }
                    highlightClickedSpecialityOrProfessor(specDOM,false);
                    setCrud('speciality',specDOM.id);
                });
            }
        });

    }

    function processProfessors() {

        let professorsUl = document.querySelector('.professors-ul');
        professorsUl.innerHTML = '';

        professors.forEach((professor) => {
            if(Object.prototype.hasOwnProperty.call(professor, 'idProfessor') && Object.prototype.hasOwnProperty.call(professor, 'professorName')) {
                let profDOM = document.createElement('li');
                profDOM.classList.add('professor-list-item');
                profDOM.id = `speciality-${professor.idProfessor}`;
                profDOM.innerHTML = `${professor.professorName}`;
                professorsUl.append(profDOM);

                //adding listener for speciality
                profDOM.addEventListener('click',function () {
                    if (!profDOM.classList.contains('clicked')) {
                        showStudentsAndSubjectsForSpeciality(profDOM.id);
                        profDOM.classList.add('clicked');
                    }
                    highlightClickedSpecialityOrProfessor(profDOM,true);
                });
            }
        });

    }

}
//if ui user clicked to speciality list item
function showStudentsAndSubjectsForSpeciality(specId) {
    let specialityLiDom = document.querySelector(`#${specId}`);
    specId = specId.substr(11);
    console.log(specId);

    let Http = new XMLHttpRequest();
    let url=`http://localhost:8080/deanery/getStudentsFromSpeciality/${specId}`;
    Http.open("GET", url);
    Http.responseType = 'json';
    Http.send();
    let specStudents = [];
    Http.onreadystatechange = (e) => {
        if(Http.readyState === 4 && Http.status === 200) {
            console.log(Http.response);
            specStudents = Http.response;
            processStudents();
        }
    }
    let Http2 = new XMLHttpRequest();
    let url2=`http://localhost:8080/deanery/getSpecialitySubjects/${specId}`;
    Http2.open("GET", url2);
    Http2.responseType = 'json';
    Http2.send();
    let specSubjects = [];
    Http2.onreadystatechange = (e) => {
        if(Http2.readyState === 4 && Http2.status === 200) {
            console.log(Http2.response);
            specSubjects = Http2.response;
            processSubjects();
        }
    }

    function processStudents() {

        let liStudent = document.createElement('li');
        liStudent.classList.add('students-list-item');
        if(specStudents.length===0) liStudent.innerHTML = 'No Students';
            else liStudent.innerHTML = 'Students';
        specialityLiDom.append(liStudent);
        let ulStudent = document.createElement('ul');
        ulStudent.classList.add('students-ul');
        liStudent.append(ulStudent);

        specStudents.forEach((student) => {

            if(Object.prototype.hasOwnProperty.call(student, 'idStudent') && Object.prototype.hasOwnProperty.call(student, 'studentName')) {
                let liStudentItem = document.createElement('li');
                liStudentItem.classList.add('student-list-item');
                liStudentItem.id = `student-${student.idStudent}`;
                liStudentItem.innerHTML = `  ${student.studentName}`;
                ulStudent.append(liStudentItem);

                //adding listener for speciality
                liStudentItem.addEventListener('click',function () {
                    console.log("im student");
                    highlightClickedStudentOrSubject(liStudentItem);
                });
            }
        });

    }

    function processSubjects () {

        let liSubject = document.createElement('li');
        liSubject.classList.add('subjects-list-item');
        if(specSubjects.length===0) liSubject.innerHTML = 'No Subjects';
        else liSubject.innerHTML = 'Subjects';
        specialityLiDom.append(liSubject);
        let ulSubject= document.createElement('ul');
        ulSubject.classList.add('subjects-ul');
        liSubject.append(ulSubject);

        specSubjects.forEach((subject) => {

            if(Object.prototype.hasOwnProperty.call(subject, 'idSubject') && Object.prototype.hasOwnProperty.call(subject, 'subjectName')) {
                let liSubjectItem = document.createElement('li');
                liSubjectItem.classList.add('student-list-item');
                liSubjectItem.id = `student-${subject.idSubject}`;
                liSubjectItem.innerHTML = `${subject.subjectName}`;
                ulSubject.append(liSubjectItem);

                //adding listener for speciality
                liSubjectItem.addEventListener('click',function () {
                    console.log("im subject");
                    highlightClickedStudentOrSubject(liSubjectItem);
                });
            }
        });

    }
}


init();
let specId = document.querySelector('.hidden-spec-id').value;
let studId = document.querySelector('.hidden-stud-id').value;
let Http2 = new XMLHttpRequest();
let url2=`http://localhost:8080/deanery/getSpecialitySubjects/${specId}`;
Http2.open("GET", url2);
Http2.responseType = 'json';
Http2.send();
let specSubjects = [];
let enrollContainer = document.querySelector('.enroll-list');

Http2.onreadystatechange = (e) => {
    if(Http2.readyState === 4 && Http2.status === 200) {
        specSubjects = Http2.response;
        console.log(specSubjects);
        specSubjects.forEach((subject) => {
            //adding list of groups for concrete subject, adding listeners to save group
            let container = document.createElement('div');
            container.classList.add('container-enroll-line');
            let name = document.createElement('div');
            name.classList.add('enroll-subject-name-header');
            name.innerHTML = subject.subjectName;
            container.append(name);
            let inputList = document.createElement('select');
            inputList.classList.add(`select-group-${subject.idSubject}`);
            let Http3 = new XMLHttpRequest();
            let url3 = `http://localhost:8080/deanery/getSubjectGroups/${subject.idSubject}`;
            Http3.open("GET", url3);
            Http3.responseType = 'json';
            Http3.send();
            let groups = [];
            Http3.onreadystatechange = (e) => {
                if (Http3.readyState === 4 && Http3.status === 200) {
                    //adding list of groups and listener for save group
                    groups = Http3.response;
                    groups.forEach((group) => {
                        inputList.innerHTML += `<option value="${group.idGroup}"> ${group.groupName} </option>`;
                    });
                    container.append(inputList);
                    let button = document.createElement('button');
                    container.append(button);
                    button.classList.add('save-group-button');
                    button.innerHTML = 'save';
                    button.addEventListener('click', function () {
                        let value = document.querySelector(`.select-group-${subject.idSubject}`).value;
                        let Http4 = new XMLHttpRequest();
                        let url4=`http://localhost:8080/student/enrollStudent/${studId}/${value}/`;
                        Http4.open("POST", url4);
                        Http4.send();
                        Http4.onreadystatechange = (e) => {
                            if (Http4.readyState === 4 && Http4.status === 200) {
                                alert("Student registered to group");
                            }
                            else if (Http4.readyState === 4 && Http4.status === 400) {
                                alert(Http4.response);
                            }
                        }
                    });
                }
                if (Http3.readyState === 4 && Http3.status === 400) {
                    let description = document.createElement('div');
                    description.innerHTML ='No groups for this subject';
                    container.append(description);
                }
            }
            enrollContainer.append(container);
        });
    }
}
//on start
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
           process();
        }
    }

    function process() {

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
                    showStudentsForSpeciality(specDOM.id);
                });
            }
        });

    }

}
//if ui user clicked to speciality list item
function showStudentsForSpeciality(specId) {
    let specialityUlDom = document.querySelector(`#${specId}`);
    specialityUlDom.innerHTML;

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
                    showStudentsForSpeciality(specDOM.id);
                });
            }
        });

    }
}


init();
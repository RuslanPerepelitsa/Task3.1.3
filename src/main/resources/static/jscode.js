let userInfo = $('#tableUsers')
let allUsers = []

getAllUsers()

function getAllUsers() {
    fetch("/api/users").then((response) => {
        console.log(response.statusText + response.status)
        if (response.ok) {
            response.json().then((users) => {
                users.forEach((user) => {
                    console.log(user)
                    addUserForTable(user)
                    allUsers.push(user)
                });
            });
            console.log(allUsers)
        } else {
            console.error(response.statusText + response.status)
        }
    });
}

function addUserForTable(user) {
    userInfo.append(
        '<tr>' +
        '<td>' + user.id + '</td>' +
        '<td>' + user.firstName + '</td>' +
        '<td>' + user.lastName + '</td>' +
        '<td>' + user.email + '</td>' +
        '<td>' + user.roles.map(roleUser => roleUser.name) + '</td>' +
        '<td>' +
        '<button onclick="editUserById(' + user.id + ')" class="btn btn-info edit-btn" data-toggle="modal" data-target="#edit"' +
        '>Edit</button></td>' +
        '<td>' +
        '<button onclick="deleteUserById(' + user.id + ')" class="btn btn-danger" data-toggle="modal" data-target="#delete"' +
        '>Delete</button></td>' +
        '</tr>'
    )
}

function addNewUser() {

    let roleList = () => {
        let array = []
        let options = document.querySelector('#addRole').options
        for (let i = 0; i < options.length; i++) {
            if (options[i].selected) {
                let role = {id: options[i].value, name: options[i].text}
                array.push(role)
            }
        }
        return array;
    }

    let user = {
        firstName: document.getElementById("addFirstName").value,
        lastName: document.getElementById("addLastName").value,
        email: document.getElementById("addEmail").value,
        password: document.getElementById("addPassword").value,
        roles: roleList()
    }


    let headers = new Headers();
    headers.append('Content-Type', 'application/json; charset=utf-8');
    let request = new Request('/api/users', {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(user)
    });
    console.log(user);

    fetch(request).then((response) => {
        response.json().then((userAdd) => {
            allUsers.push(userAdd)
            addUserForTable(userAdd)
            console.log(userAdd)
        })

        console.log(allUsers)

        $('#usersTableActive').tab('show');
        userClearModal()
    })
}

function userClearModal() {
    $('#addFirstName').empty().val('');
    $('#addLastName').empty().val('');
    $('#addEmail').empty().val('');
    $('#addPassword').empty().val('');
    $('#addRole').val('');

}

function editUserById(id) {
    fetch("/api/users/" + id, {method: "GET", dataType: 'json',})
        .then((response) => {
            response.json().then((user) => {
                $('#editId').val(user.id);
                $('#editFirstName').val(user.firstName);
                $('#editLastName').val(user.lastName);
                $('#editEmail').val(user.email);
                $('#editPassword').val(user.password);
                $('#editRole').val(user.roles.map(role=>role.name));

                console.log(user)
            })
        })
}

function editButton() {
    let roleList = () => {
        let array = []
        let options = document.querySelector('#editRole').options
        for (let i = 0; i < options.length; i++) {
            if (options[i].selected) {
                let role = {id: options[i].value, name: options[i].text}
                array.push(role)
            }
        }
        return array;
    }

    let editUser = {
        id: document.getElementById("editId").value,
        firstName: document.getElementById("editFirstName").value,
        lastName: document.getElementById("editLastName").value,
        email: document.getElementById("editEmail").value,
        password: document.getElementById("editPassword").value,
        roles: roleList()
    }
    console.log(editUser);

    let headers = new Headers();
    headers.append('Content-Type', 'application/json; charset=utf-8');
    let request = new Request("api/users", {
        method: 'PUT',
        headers: headers,
        body: JSON.stringify(editUser),
    });

    let userEditId = ($('#editId').val())
    console.log(userEditId)
    fetch(request).then((response) => {
        response.json().then((userEdit) => {
            console.log(userEdit);
            userInfo.empty();
            allUsers = allUsers.map(user => user.id !== userEdit.id ? user : userEdit)
            console.log(allUsers)
            allUsers.forEach((user) => {
                addUserForTable(user)
            })
        })
        $('#edit').modal('hide');
    });
}

function deleteUserById(id) {
    fetch("/api/users/" + id, {method: "GET", dataType: 'json',})
        .then((response) => {
            response.json().then((user) => {
                $('#deleteId').val(user.id)
                $('#deleteFirstName').val(user.firstName)
                $('#deleteLastName').val(user.lastName)
                $('#deleteEmail').val(user.email)
                $('#deletePassword').val(user.password)
                $('#deleteRole').val(user.roles)
            })
        })
}

function deleteButton() {
    let userId = ($('#deleteId').val());
    console.log(userId)
    fetch("/api/users/" + userId, {method: "DELETE"})
        .then((response) => {
            userInfo.empty()
            allUsers = allUsers.filter(user => user.id !== Number(userId))
            console.log(allUsers)

            allUsers.forEach((user) => {
                addUserForTable(user)
            })
            $('#delete').modal('hide');
        })
}




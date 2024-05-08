// Datos de ejemplo de usuarios
let users = [
    { id: 1, nombre: "Juan", ciudad: "Ciudad de México", estado: "Activo" },
    { id: 2, nombre: "María", ciudad: "Guadalajara", estado: "Inactivo" },
    { id: 3, nombre: "Carlos", ciudad: "Monterrey", estado: "Activo" }
  ];
  
  // Función para mostrar los usuarios en la tabla
  function displayUsers(users) {
    const tableBody = document.getElementById("clientTableBody");
    tableBody.innerHTML = "";
    users.forEach(user => {
      const row = `<tr data-user-id="${user.id}">
                    <td>${user.id}</td>
                    <td>${user.nombre}</td>
                    <td>${user.ciudad}</td>
                    <td>${user.estado}</td>
                    <td>
                      <button class="btn btn-info editBtn">Editar</button>
                      <button class="btn btn-danger deleteBtn">Eliminar</button>
                      <button class="btn btn-secondary toggleStateBtn">${user.estado === "Activo" ? "Desactivar" : "Activar"}</button>
                    </td>
                  </tr>`;
      tableBody.innerHTML += row;
    });
  }
  
  // Función para agregar un nuevo usuario
  function addUser(newUser) {
    users.push(newUser);
    displayUsers(users);
    alert("Usuario agregado correctamente.");
  
    // Limpiar el formulario de agregar usuario y ocultarlo
    document.getElementById("addClientFormContainer").innerHTML = "";
  }
  
  // Función para filtrar usuarios por nombre, ciudad o estado
  function filterUsers(query) {
    return users.filter(user =>
      user.nombre.toLowerCase().includes(query.toLowerCase()) ||
      user.ciudad.toLowerCase().includes(query.toLowerCase()) ||
      user.estado.toLowerCase().includes(query.toLowerCase())
    );
  }
  
  // Función para manejar la búsqueda de usuarios
  function handleSearch() {
    const query = document.getElementById("searchInput").value;
    const filteredUsers = filterUsers(query);
    displayUsers(filteredUsers);
  }
  
  // Mostrar la gestión de usuarios al hacer clic en el botón del menú lateral
  document.getElementById("clientesMenuBtn").addEventListener("click", function() {
    document.getElementById("clientsSection").style.display = "block";
    displayUsers(users);
  });
  
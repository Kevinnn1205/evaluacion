 // Ejemplo de datos de clientes (puedes reemplazar esto con datos de tu backend)
 let clients = [
  { 
    id: 1,
    tipoDocumento: "DNI",
    numeroDocumento: "12345678",
    nombre: "Juan",
    apellido: "Pérez",
    telefono: "555-1234",
    direccion: "Av. Principal 123",
    ciudad: "Ciudad de México",
    estado: "Activo"
  },
  { 
    id: 2,
    tipoDocumento: "Cédula",
    numeroDocumento: "87654321",
    nombre: "María",
    apellido: "García",
    telefono: "555-5678",
    direccion: "Calle Secundaria 456",
    ciudad: "Guadalajara",
    estado: "Inactivo"
  },
  { 
    id: 3,
    tipoDocumento: "DNI",
    numeroDocumento: "98765432",
    nombre: "Carlos",
    apellido: "López",
    telefono: "555-9012",
    direccion: "Plaza Central 789",
    ciudad: "Monterrey",
    estado: "Activo"
  }
];

// Función para mostrar los clientes en la tabla
function displayClients(clients) {
  const tableBody = document.getElementById("clientTableBody");
  tableBody.innerHTML = "";
  clients.forEach(client => {
    const row = `<tr data-client-id="${client.id}">
                  <td>${client.id}</td>
                  <td>${client.tipoDocumento}</td>
                  <td>${client.numeroDocumento}</td>
                  <td>${client.nombre}</td>
                  <td>${client.apellido}</td>
                  <td>${client.telefono}</td>
                  <td>${client.direccion}</td>
                  <td>${client.ciudad}</td>
                  <td>${client.estado}</td>
                  <td>
                    <button class="btn btn-sm btn-info editBtn">Editar</button>
                    <button class="btn btn-sm btn-danger deleteBtn">Eliminar</button>
                    <button class="btn btn-sm btn-secondary toggleStateBtn">${client.estado === "Activo" ? "Desactivar" : "Activar"}</button>
                  </td>
                </tr>`;
    tableBody.innerHTML += row;
  });
}

// Función para mostrar el formulario de agregar cliente
function showAddClientForm() {
  const formContainer = document.getElementById("addClientFormContainer");
  formContainer.innerHTML = `
    <h3>Agregar Cliente</h3>
    <form id="addClientForm">
      <div class="form-group">
        <label for="tipoDocumento">Tipo de Documento:</label>
        <input type="text" class="form-control" id="tipoDocumento" required>
      </div>
      <div class="form-group">
        <label for="numeroDocumento">Número de Documento:</label>
        <input type="text" class="form-control" id="numeroDocumento" required>
      </div>
      <div class="form-group">
        <label for="nombre">Nombre:</label>
        <input type="text" class="form-control" id="nombre" required>
      </div>
      <div class="form-group">
        <label for="apellido">Apellido:</label>
        <input type="text" class="form-control" id="apellido" required>
      </div>
      <div class="form-group">
        <label for="telefono">Teléfono:</label>
        <input type="text" class="form-control" id="telefono" required>
      </div>
      <div class="form-group">
        <label for="direccion">Dirección:</label>
        <input type="text" class="form-control" id="direccion" required>
      </div>
      <div class="form-group">
        <label for="ciudad">Ciudad:</label>
        <input type="text" class="form-control" id="ciudad" required>
      </div>
      <div class="form-group">
        <label for="estado">Estado:</label>
        <select class="form-control" id="estado" required>
          <option value="Activo">Activo</option>
          <option value="Inactivo">Inactivo</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary">Agregar</button>
    </form>
  `;
  // Agregar evento submit para el formulario de agregar cliente
  document.getElementById("addClientForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const newClient = {
      id: clients.length + 1,
      tipoDocumento: document.getElementById("tipoDocumento").value,
      numeroDocumento: document.getElementById("numeroDocumento").value,
      nombre: document.getElementById("nombre").value,
      apellido: document.getElementById("apellido").value,
      telefono: document.getElementById("telefono").value,
      direccion: document.getElementById("direccion").value,
      ciudad: document.getElementById("ciudad").value,
      estado: document.getElementById("estado").value
    };
    addClient(newClient);
  });
}

// Función para agregar un nuevo cliente
function addClient(newClient) {
  clients.push(newClient);
  displayClients(clients);
  alert("Cliente registrado correctamente.");

  // Limpiar el formulario de agregar cliente y ocultarlo
  document.getElementById("addClientFormContainer").innerHTML = "";
}

// Función para filtrar clientes por nombre, ciudad o estado
function filterClients(query) {
  return clients.filter(client =>
    client.nombre.toLowerCase().includes(query.toLowerCase()) ||
    client.ciudad.toLowerCase().includes(query.toLowerCase()) ||
    client.estado.toLowerCase().includes(query.toLowerCase())
  );
}

// Función para mostrar los clientes filtrados
function handleSearch() {
  const query = document.getElementById("searchInput").value;
  const filteredClients = filterClients(query);
  displayClients(filteredClients);
}

// Función para mostrar la gestión de clientes
function showClientsManagement() {
  const clientsSection = document.getElementById("clientsSection");
  clientsSection.style.display = "block"; // Mostrar la sección de clientes
  displayClients(clients); // Mostrar la lista de clientes
}

// Manejar clic en el botón de "Clientes" del menú lateral
document.getElementById("clientesMenuBtn").addEventListener("click", function() {
  showClientsManagement();
});

// Función para mostrar el formulario de edición con los datos del cliente seleccionado
function showEditClientForm(clientId) {
  const clientToEdit = clients.find(client => client.id === clientId);

  // Llenar el formulario con los datos del cliente seleccionado
  document.getElementById("tipoDocumento").value = clientToEdit.tipoDocumento;
  document.getElementById("numeroDocumento").value = clientToEdit.numeroDocumento;
  document.getElementById("nombre").value = clientToEdit.nombre;
  document.getElementById("apellido").value = clientToEdit.apellido;
  document.getElementById("telefono").value = clientToEdit.telefono;
  document.getElementById("direccion").value = clientToEdit.direccion;
  document.getElementById("ciudad").value = clientToEdit.ciudad;
  document.getElementById("estado").value = clientToEdit.estado;

  // Cambiar el texto del botón de enviar a "Guardar cambios"
  document.getElementById("submitBtn").innerText = "Guardar cambios";
  // Agregar un atributo data-client-id al formulario de edición con el ID del cliente seleccionado
  document.getElementById("editClientForm").setAttribute("data-client-id", clientId);
  // Mostrar el formulario de edición
  document.getElementById("editClientFormContainer").style.display = "block";
}

// Manejar clic en el botón de "Editar" en la tabla de clientes
document.addEventListener("click", function(event) {
  if (event.target.classList.contains("editBtn")) {
    const clientId = parseInt(event.target.closest("tr").getAttribute("data-client-id"));
    showEditClientForm(clientId);
  }
});

// Función para manejar el envío del formulario de edición
document.getElementById("editClientForm").addEventListener("submit", function(event) {
  event.preventDefault();
  // Obtener el ID del cliente seleccionado del atributo data-client-id
  const clientId = parseInt(this.getAttribute("data-client-id"));
  // Encontrar el cliente en la lista de clientes
  const clientIndex = clients.findIndex(client => client.id === clientId);
  // Actualizar los datos del cliente con los valores del formulario
  clients[clientIndex].tipoDocumento = document.getElementById("tipoDocumento").value;
  clients[clientIndex].numeroDocumento = document.getElementById("numeroDocumento").value;
  clients[clientIndex].nombre = document.getElementById("nombre").value;
  clients[clientIndex].apellido = document.getElementById("apellido").value;
  clients[clientIndex].telefono = document.getElementById("telefono").value;
  clients[clientIndex].direccion = document.getElementById("direccion").value;
  clients[clientIndex].ciudad = document.getElementById("ciudad").value;
  clients[clientIndex].estado = document.getElementById("estado").value;

  // Mostrar una notificación de actualización
  alert("Los datos del cliente han sido actualizados correctamente.");

  // Limpiar el formulario de edición y ocultarlo
  document.getElementById("editClientForm").reset();
  document.getElementById("editClientFormContainer").style.display = "none";

  // Volver a mostrar la lista actualizada de clientes
  displayClients(clients);
});
// Función para manejar la búsqueda de clientes
function handleSearch() {
  const query = document.getElementById("searchInput").value; // Obtener el valor del campo de búsqueda
  const filteredClients = filterClients(query); // Filtrar los clientes según la búsqueda
  displayClients(filteredClients); // Mostrar los clientes filtrados
}

// Manejar cambios en el campo de búsqueda
document.getElementById("searchInput").addEventListener("input", handleSearch);

// Manejar clic en el botón de "Agregar Cliente"
document.getElementById("addClientBtn").addEventListener("click", function() {
  showAddClientForm();
});

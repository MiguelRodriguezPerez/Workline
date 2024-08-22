import * as valBusca from '/js/functionSnippets/validarUsuario.js';

'use strict'

const formulario = document.getElementById('formularioDatos');
const arrInputs = Array.from(document.querySelectorAll('.usuarioInput'));
const arrFallos = Array.from(document.querySelectorAll('.usuarioError'));
const arrFunciones = [
    valBusca.validarNombreUsuario,
    valBusca.validarEmail,
    valBusca.validarPassword,
    valBusca.validarCiudad,
    valBusca.validarTelefono
];

for(let i = 0; i < arrFunciones.length; i++){
    arrInputs[i].addEventListener('input', () => {
        arrFunciones[i](arrInputs[i], arrFallos[i]);
    });
};

document.getElementById('rol').addEventListener('change', () => {
    valBusca.validarRol(document.getElementById('rol'), arrFallos[arrFallos.length - 1]);
});


document.getElementById('subirUsuario').addEventListener('click', async (e) => {
    e.preventDefault();

    let confirmarValidaciones = true;

    // Realiza las validaciones de forma asíncrona
    for(let i = 0; i < arrFunciones.length && confirmarValidaciones === true; i++){
        confirmarValidaciones = await arrFunciones[i](arrInputs[i], arrFallos[i]); // Usar await para esperar cada validación
    }

    if(confirmarValidaciones === true){
        confirmarValidaciones = valBusca.validarRol(document.getElementById('rol'), arrFallos[arrFallos.length - 1]);
    }
    
    if(confirmarValidaciones === true) formulario.submit();
});
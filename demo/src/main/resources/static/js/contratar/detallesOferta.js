"use strict"

document.getElementById('editar').addEventListener('click', activarEdicion);

function activarEdicion() {
    for (const i of document.getElementsByTagName('input')) {
        if (i.readOnly === true) i.readOnly = false;
    }

    for (const s of document.getElementsByTagName('select')) {
        s.disabled = false;
    }

    document.getElementById('descripcion').removeAttribute('readonly');
    document.getElementById('subirCambios').style.display = 'block';

    const botonEditar = document.getElementById('editar');
    botonEditar.textContent = 'Cancelar';
    botonEditar.id = 'cancelar';

    botonEditar.removeEventListener('click', activarEdicion);
    botonEditar.addEventListener('click', desactivarEdicion);
}

function desactivarEdicion() {
    for (const i of document.getElementsByTagName('input')) {
        if (i.readOnly === false) i.readOnly = true;
    }

    for (const s of document.getElementsByTagName('select')) {
        s.disabled = true;
    }

    document.getElementById('descripcion').setAttribute('readonly', true);
    document.getElementById('subirCambios').style.display = 'none';

    const botonCancelar = document.getElementById('cancelar');
    botonCancelar.textContent = 'Editar Oferta';
    botonCancelar.id = 'editar';

    botonCancelar.removeEventListener('click', desactivarEdicion);
    botonCancelar.addEventListener('click', activarEdicion);
}

document.getElementById('subirCambios').addEventListener('click', (e) =>{
    e.preventDefault();
    const formulario = document.getElementById('formularioEdicion');
    console.log(document.getElementsByTagName('datosOcultos'));

    if(validarOferta()) formulario.submit();
});

function validarOferta(){
    const puesto = document.getElementById('puesto').value;
    const descripcion = document.getElementById('descripcion').value;

    if(puesto.length > 30 || puesto === ''){
        document.getElementById('err1').style.display = 'block';
        document.getElementById('puesto').classList.add('inputError');
        return false;
    }
    else{
        document.getElementById('err1').style.display = 'none';
        document.getElementById('puesto').classList.remove('inputError');
    }

    if(descripcion.length > 50){
        document.getElementById('err2').style.display = 'block';
        document.getElementById('descripcion').classList.add('descripcionError');
        return false;
    } 
    else{
        document.getElementById('err2').style.display = 'none';
        document.getElementById('descripcion').classList.remove('descripcionError');
    }

    if(document.getElementById('ciudad') === ''){
        document.getElementById('err3').style.display = 'block';
        document.getElementById('ciudad').classList.add('inputError');
        return false;
    }
    else{
        document.getElementById('err3').style.display = 'none';
        document.getElementById('ciudad').classList.remove('inputError');
    }

    return true;
}
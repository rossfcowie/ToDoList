"use strict";
var selectedTask = 0;
getTasks(setTasks);
function getTasks(use){
    fetch("http://localhost:8088/Task").then((res)=>{
      if (res.status !== 200) {
        console.log(
          `Looks like there was a problem.Status Code: ${res.status}`
        );
        return;
      }
      res.json()
      .then(data => {use(data);})
    }).catch((err) => console.log(err));
}

function loadTask(tasks){
  let portal = document.getElementById("formModalPortal")
  tasks.forEach(task=>{
    if(task.id == selectedTask){
      portal.contentWindow.open(task)
    }
  })
  
}


function setTasks(tasks){
console.log(tasks);
let tList = document.querySelector("#TasksList");
if(tList != null){
  tList.innerHTML =""
  tasks.forEach(task => {
    tList.appendChild(createTaskBox(task,task.id));
    getSteps(steps,task.id);
});
}else{
  console.log("tlistnull")
}

}

function createTaskBox(task,i){
    let tbox = document.createElement("div")
    tbox.setAttribute("class","row")
    tbox.setAttribute("id","Task"+i +  "Container")
    tbox.setAttribute("onClick","modify("+i+")")
    let container = document.createElement("div")
    container.setAttribute("class","Container")
    let inner = document.createElement("div")
    inner.setAttribute("class","row")
    let text = document.createElement("div")
    text.setAttribute("class","col-7")
    text.innerHTML = `<div class="row"><h2>${task.nameString}</h2>
    </div><div class="row"><hr /></div> 
    <div class="row"><h4>${task.description}</h4></div>`;
    let steps = document.createElement("div")
    steps.setAttribute("class","col-5")
    steps.innerHTML = `<div class="row">
    <div class="ContainerScrollable" id="task${i}StepContainer">
    </div>
  </div>`
    inner.appendChild(text)
    inner.appendChild(steps)
    container.appendChild(inner)
    tbox.appendChild(container)
    return tbox
}

function deleteTask(id){
  fetch("http://localhost:8088/Task/"+ id , { 
    method: 'delete'
    }).then((res)=>{
        if (res.status !== 200) {
          console.log(
            `Looks like there was a problem.Status Code: ${res.status}`
          );
          return;
        }
        res.json()
      }).then(data => {modify(id);
        getTasks(setTasks);})
      .catch((err) => console.log(err));
      
}



function steps(steps,i){
    let stepContainer = document.getElementById(`task${i}StepContainer`)
    steps.forEach(step => {

        let completed = ""
        if(step.complete){
            completed = "checked"
        }
        let sbox = document.createElement("div")
        sbox.innerHTML = `
        <div class="Container${step.id}">
          <input
            type="checkbox"
            onclick="ToggleStep(${step.id})"
            id="task${step.id}Check"
            ${completed}
            /><span class="StepText">${step.name}</span></div>`
          stepContainer.appendChild(sbox)
    })
}

function getSteps(use,i){
    fetch("http://localhost:8088/Step/"+i).then((res)=>{
      
      if (res.status !== 200) {
        console.log(
          `Looks like there was a problem.Status Code: ${res.status}`
        );
        return;
      }
      res.json()
      .then(data => {use(data,i);})
    }).catch((err) => console.log(err));
}

function ToggleStep(i){
    
    fetch("http://localhost:8088/Step/f/"+i , { 
    method: 'put'
    }).then((res)=>{
        
        if (res.status !== 200) {
          console.log(
            `Looks like there was a problem.Status Code: ${res.status}`
          );
          return;
        }
        res.json()
      }).catch((err) => console.log(err));
      
  }
  
  function update(){
  
  getTasks(setTasks);

  }


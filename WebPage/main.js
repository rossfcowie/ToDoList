"use strict";

function getTasks(){
    fetch("http://localhost:8088/Task").then((res)=>{
      console.log(res);
      if (res.status !== 200) {
        console.log(
          `Looks like there was a problem.Status Code: ${res.status}`
        );
        return;
      }
      res.json()
      .then(data => {setTasks(data);})
    }).catch((err) => console.log(err));
}

getTasks();

function setTasks(tasks){
console.log(tasks);
let tList = document.getElementById("TasksList")
tList.innerHTML="";
tasks.forEach(task => {
    console.log(task);
    tList.appendChild(createTaskBox(task,task.id));
    getSteps(task.id);
});
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
    steps.setAttribute("class","col-4")
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

function steps(steps,i){
    let stepContainer = document.getElementById(`task${i}StepContainer`)
    steps.forEach(step => {
        console.log(step)
        let completed = ""
        if(step.complete){
            completed = "checked"
        }
        let sbox = document.createElement("div")
        sbox.innerHTML = `
        <div class="Container">
          <input
            type="checkbox"
            onclick="ToggleTask(${step.id})"
            id="task${step.id}Check"
            ${completed}
            /><span class="StepText">${step.name}</span></div>`
          stepContainer.appendChild(sbox)
    })
}

function getSteps(i){
    fetch("http://localhost:8088/Step/"+i).then((res)=>{
      console.log(res);
      if (res.status !== 200) {
        console.log(
          `Looks like there was a problem.Status Code: ${res.status}`
        );
        return;
      }
      res.json()
      .then(data => {steps(data,i);})
    }).catch((err) => console.log(err));
}


function demo() {
    alert("thymeleaf with js and css");
}



/*window.onload = function(){
    //alert("hello");
const exampleModal = document.getElementById('exampleModal');
//alert("tsst");
if (exampleModal) {
  exampleModal.addEventListener('show.bs.modal', (event) => {
    // Button that triggered the modal
    //console.log("789");
    const button = event.relatedTarget;

    // Extract info from data-bs-* attributes
    const recipient = button.getAttribute('data-bs-whatever');
    // If necessary, you could initiate an Ajax request here
    // and then do the updating in a callback.

    // Update the modal's content.
    const modalTitle = exampleModal.querySelector('.modal-title');
    const modalBodyInput = exampleModal.querySelector('.modal-body input');
    //console.log("the recipient is "+recipient);
    modalTitle.textContent = `New message to ${recipient}`;
    modalBodyInput.value = recipient;
  });
}

};*/

function handleAction(event) {
    var finditem = $(event.currentTarget).closest('.input-group.currentlistitem');
    console.log("Handled action for:", finditem);
    console.log("hello world");
    //console.log("************");
    // console.log("Event type:", event.type); // e.g., "click", "focusout"
    //    console.log("Target element:", event.target); // The element that triggered the event
    //    console.log("Current element:", event.currentTarget); // The element the handler is attached
    //    console.log("check box "+event.target.checked);
    //    console.log("value  "+event.target.value);
    //debugger; finditem.find('.form-control').val()

     var updateobject = {
        "detail": finditem.find('.form-control').val(),
        "itemstate": event.target.checked,
        "id": finditem.attr("name")
     };
console.log("************");
     console.log(updateobject);
     console.log("@@@@@@@@@@@@@@");
     //debugger;
     var token = document.querySelector(".currentform input").value;
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     $.ajax({
                  url: "/"+$('.d-flex.currentform .d-none').text(), // Endpoint to fetch updated data
                  method: "PUT",
                  headers:{
                              "X-CSRF-TOKEN": token // Correct CSRF header name for Spring Boot
                              },
                              contentType: "application/json",
                               data: JSON.stringify(updateobject),
                               success: function(response) {//edit-list ms-5 d-flex
                                           //$(".edit-list.ms-5.d-flex").html(response); // Update the table with new data
                                           alert("update success");
                                           //ul.appendChild(li);
                                           //input.value = "";
                                           //console.log("the response is ");
                                           //console.log(response);
                                           //eventlistenerforlistdetail(true);

                                                                 //additemtocurrentlist(event);
                                                                 },
                                                                 error: function() {
                                                                        alert("Error11 fetching data.");
                                                                  }
                  });



     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}

function eventlistenerforlistdetail(onlylast = false){

        var detaillist =  $(".input-group.currentlistitem");

        if (detaillist.length) {


        detaillist.each(function(index,item){
            var $item = $(item);

            var detailname = $item.attr('name');
            //find child
            //find checkbox

            if (onlylast && !$item.is(detaillist.last())){
                return;
            }


            $item.find('.form-check-input').on('click',handleAction);

            //find text area
            $item.find('.form-control').on('focusout', handleAction);

            //$item.find('.form-check-input')
            //$item.on('click', function() {
            //                            console.log("Item clicked:", detailname);
            //                        });

        });
        }

/*
if (detaillist.length) {
    detaillist.each(function(index, item) {
        // Convert the raw DOM element to a jQuery object
        var $item = $(item);

        // Get the 'name' attribute of the current item
        var detailname = $item.attr('name');

        // Add a click event listener to the item
        $item.on('click', function() {
            console.log("Item clicked:", detailname);
        });

        // Add a change event listener to the checkbox inside the item
        $item.find('.form-check-input').on('change', function() {
            alert("Checkbox changed for: " + detailname);
        });
    });
}
*/

}

function deletedetail(event) {

    debugger;
    console.log("hello world");
    var name = $(event.currentTarget).closest('.input-group.currentlistitem').attr("name");
    //name $(event.currentTarget).closest('.input-group.currentlistitem').attr("name")
    var token = document.querySelector(".currentform input").value;
    $.ajax({
                      url: "/detail/"+name, // Endpoint to fetch updated data
                      method: "DELETE",
                      headers:{
                                  "X-CSRF-TOKEN": token // Correct CSRF header name for Spring Boot
                                  },
                                   //contentType: "application/json",
                                   //data: JSON.stringify(updateobject),
                                   success: function(response) {//edit-list ms-5 d-flex
                                               //$(".edit-list.ms-5.d-flex").html(response); // Update the table with new data

                                               $(`*[name=${name}]`).closest('li').remove();
                                               alert("delete success");
                                               //ul.appendChild(li);
                                               //input.value = "";
                                               //console.log("the response is ");
                                               //console.log(response);
                                               //eventlistenerforlistdetail(true);

                                                                     //additemtocurrentlist(event);
                                                                     },
                                                                     error: function() {
                                                                            alert("Error11 fetching data.");
                                                                      }
                      });



}

function deletelist(event){
    //debugger;
    event.stopPropagation();
    if (window.confirm("Do you confirm to delete this todolist?")) {
    //d-none    //$("#2").closest('form').remove()
    var deletelistid = $(event.currentTarget).closest('form').find('span[name="idd"].d-none').text();
    var token = $(event.currentTarget).closest('form').find('*[name="_csrf"]').val();
        //////////////////////////////////////////////////////////////////////////////////////
    $.ajax({
                          url: "/todolist/"+deletelistid, // Endpoint to fetch updated data
                          method: "DELETE",
                          headers:{
                                      "X-CSRF-TOKEN": token // Correct CSRF header name for Spring Boot
                                      },
                                       //contentType: "application/json",
                                       //data: JSON.stringify(updateobject),
                                       success: function(response) {//edit-list ms-5 d-flex
                                                   //$(".edit-list.ms-5.d-flex").html(response); // Update the table with new data

                                                   $(`#${deletelistid}`).closest('form').remove();
                                                   alert("delete LIST success");
                                                   $(".edit-list.ms-5.d-flex.container").html("No selected list");
                                                   //ul.appendChild(li);
                                                   //input.value = "";
                                                   //console.log("the response is ");
                                                   //console.log(response);
                                                   //eventlistenerforlistdetail(true);

                                                                         //additemtocurrentlist(event);
                                                                         },
                                                                         error: function() {
                                                                                alert("Error from deleteing list data.");
                                                                          }
                          });

        /////////////////////////////////////////////////////////////////////////////////////////
    }
}

window.onload = function(){
    //alert("hello");
const exampleModal = document.getElementById('exampleModal');
//alert("tsst");
if (exampleModal) {
  exampleModal.addEventListener('show.bs.modal', (event) => {
    // Button that triggered the modal
    //console.log("789");
    const button = event.relatedTarget;

    // Extract info from data-bs-* attributes
    const recipient = button.getAttribute('data-bs-whatever');
    // If necessary, you could initiate an Ajax request here
    // and then do the updating in a callback.

    // Update the modal's content.
    const modalTitle = exampleModal.querySelector('.modal-title');
    /*const modalBodyInput = exampleModal.querySelector('.modal-body input');*/
    const modalBodyInput = exampleModal.querySelector('.modal-body input[name="inputname"]');
    //console.log("the recipient is "+recipient);
    if (recipient && modalBodyInput) {
    modalTitle.textContent = `New message to ${recipient}`;
    modalBodyInput.value = recipient;
    }
  });
}

//////////////for icon////////////////////////////////
 /*function selectIcon(button) {
      document.getElementById("selectedIcon").value = button.getAttribute("data-icon");
  }

  document.getElementById("iconSearch").addEventListener("input", function () {
      let searchValue = this.value.toLowerCase();
      document.querySelectorAll(".icon-btn").forEach(btn => {
          let iconName = btn.getAttribute("data-icon");
          btn.style.display = iconName.includes(searchValue) ? "inline-block" : "none";
      });
  });*/


////////////////////////////////////////////

};

function _uuid() {
  var d = Date.now();
  if (typeof performance !== 'undefined' && typeof performance.now === 'function'){
    d += performance.now(); //use high-precision timer if available
  }
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    var r = (d + Math.random() * 16) % 16 | 0;
    d = Math.floor(d / 16);
      return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
  });
}


document.addEventListener("DOMContentLoaded", function () {
    console.log("DOM fully loaded");

    ///////nav bar logout part//////

                           //.on( "mouseenter", handlerIn ).on( "mouseleave", handlerOut )

    $(".logoutcontainer").on( "mouseenter", function(event){
                        event.stopPropagation();
                        $(this).find(".avater").addClass("d-none",1500);
                        $(this).find("#logoutfunction").removeClass("d-none",1500);
                     }).on("mouseleave", function(event){
                        event.stopPropagation();
                        $(this).find(".avater").removeClass("d-none",1500);
                        $(this).find("#logoutfunction").addClass("d-none",1500);

                        })


    ////////////

    $("#todoSearch").on("input", function() {
            let searchValue = $(this).val().toLowerCase();
            $(".todosidebar").each(function() {
                debugger;
                let title = $(this).find(".mb-1 span").text().toLowerCase();
                //$(this).style.display = title.includes(searchValue) ? "block" : "none";
                $(this).toggle(title.includes(searchValue));
            });
        });


    $("#addlistbutton").on("click",function(event){
        console.log("clear event");
        debugger;
        history.replaceState(null, null, window.location.href);
    })


    function additemtocurrentlist(event){
                    var input = document.getElementById("userinput");

                    //console.log(input);
                    //var ul = document.getElementById("currentul");
                    //console.log(ul);


                    function additem() {
                        if (input.value.trim()==""){
                                     alert("Please enter the item");
                                     return false;
                               }

                        var ul = document.getElementById("currentul");
                        if (!ul) return;
                        var uuidforname = _uuid();
                        var li = document.createElement("li");
                        li.innerHTML = `
                            <div class="input-group mb-3 d-flex align-items-center justify-content-center currentlistitem" name="${uuidforname}">
                                <div class="input-group-text">
                                    <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input" />
                                </div>
                                    <input class="form-control" value="${input.value}" type="text" />
                                <button class="input-group-text" type="button" onclick="deletedetail(event)">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </div>
                        `;
                        //ul.appendChild(li);
                        //input.value = ""; // Clear input after adding item


                        //const token = readCookie(CSRFTOKEN);
                        const token = document.querySelector(".currentform input").value;
                        //var formData = {
                        //    "newdetail":$('#userinput').val(),
                        //    "newid":uuidforname
                        //};
                        //$("#currentsubmitform").serializeArray().forEach(function(item) {
                        //    alert(JSON.stringify(item));
                        //    formData[item.name] = item.value;
                        //});

                        $.ajax({
                                                  url: "/"+$('.d-flex.currentform .d-none').text(), // Endpoint to fetch updated data
                                                  method: "POST",
                                                  headers:{
                                                                 "X-CSRF-TOKEN": token // Correct CSRF header name for Spring Boot
                                                              },
                                                  contentType: "application/json",
                                                  data: JSON.stringify({
                                                                         "detail":$('#userinput').val(),
                                                                          "id":uuidforname
                                                                           }),
                                                  success: function(response) {//edit-list ms-5 d-flex
                                                                //$(".edit-list.ms-5.d-flex").html(response); // Update the table with new data
                                                                alert("it worldk");
                                                                ul.appendChild(li);
                                                                input.value = "";
                                                                //console.log("the response is ");
                                                                //console.log(response);
                                                                eventlistenerforlistdetail(true);
                                                                if (Array.isArray(response)) {
                                                                            response.forEach(item => {
                                                                                console.log(item); // Log each item in the response
                                                                            });
                                                                      }
                                                                //additemtocurrentlist(event);
                                                                    },
                                                                    error: function() {
                                                                                alert("Error fetching data.");
                                                                    }
                                              });
                        }


                    var button = document.getElementById("currententer");
                    //console.log(button);
                    if (button){
                    button.addEventListener("click", function(event) {
                      event.preventDefault();
                      //alert("hello world");
                      additem();
                      /*var li = document.createElement("li");

                      li.className = '';

                      li.innerHTML = `
                            <div class="input-group mb-3 d-flex align-items-center justify-content-center currentlistitem">
                                <div class="input-group-text">
                                    <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
                                </div>
                                <div class="form-control">
                                    ${input.value}
                                </div>
                                <button class="input-group-text">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </div>
                      `;
                      ul.appendChild(li);*/
                    });
                    }

                    if (input) {
                        input.addEventListener("keydown", function(event) {
                             if (event.key === "Enter") {
                                 event.preventDefault(); // Prevent form submission
                                 additem();
                                }
                            });
                        }

            }

    // Ensure modal content updates correctly
   /* const exampleModal = document.getElementById("exampleModal");
    if (exampleModal) {
        exampleModal.addEventListener("show.bs.modal", function (event) {
            const button = event.relatedTarget;
            const recipient = button.getAttribute("data-bs-whatever");
            const modalTitle = exampleModal.querySelector(".modal-title");
            const modalBodyInput = exampleModal.querySelector('.modal-body input[name="inputname"]');

            if (recipient && modalBodyInput) {
                modalTitle.textContent = `New message to ${recipient}`;
                modalBodyInput.value = recipient;
            }
        });
    }*/

    // ***** Icon Selection (Fix event delegation issue) *****
    document.addEventListener("click", function (event) {
        if (event.target.closest(".icon-btn")) {
            let button = event.target.closest(".icon-btn");
            let selectedIcon = button.getAttribute("data-icon");
            document.getElementById("selectedIcon").value = selectedIcon;
            console.log("Selected icon:"+ selectedIcon);
        }
    });

    // ***** Icon Search *****
    document.getElementById("iconSearch").addEventListener("input", function () {
        let searchValue = this.value.toLowerCase();
        document.querySelectorAll(".icon-btn").forEach(btn => {
            let iconName = btn.getAttribute("data-icon");
            btn.style.display = iconName.includes(searchValue) ? "inline-block" : "none";
        });
    });

    ///add the "active" class to drawer
     const draweritem =  document.querySelectorAll(".list-group-item-action");

    draweritem.forEach(item => {
      item.addEventListener('click', () =>{
            //debugger;
            if (!item.classList.contains("active")){
                draweritem.forEach(i => i.classList.remove("active"));
            }
            item.classList.toggle("active");

            ////if no "active" change the html to no list selected
            let currentlistflag = false;
            draweritem.forEach(item=>{
                if (item.classList.contains("active")){
                    currentlistflag = true;
                }
            });

            if (currentlistflag==false){
                //content of current list
                //document.querySelector(".edit-list.ms-5.d-flex").childNodes[0].innerHTML = "No selected list";
                $(".edit-list.ms-5.d-flex").html("No list selected");
                history.pushState(null, "", "http://localhost:8080/");
            }

      });
    });





       let lists = []; // Array to store lists

        // Function to handle adding new list
        function addNewList(event) {
            //event.preventDefault(); // Prevent page reload

            // Get input values
            let listName = document.getElementById("recipient-name").value.trim();
            let selectedIcon = document.getElementById("selectedIcon").value;

            if (listName === "") {
                alert("Please enter a list name.");
                event.preventDefault();
                return false;
            }

            //*****************************************************
            //let formData = new FormData(this); // Get form data
            //event.preventDefault();
            //console.log(formData);
                    // Send form data to the backend using fetch API
            //        fetch("/", {
            //            method: "POST",
            //            body: formData
            //        })
            //        .then(response => response.text()) // Convert response to text (or JSON if needed)
               //     .then(data => {
             //           console.log("Server response:", data);
             //           });
            //******************************************************/
            // Add new list to the array
            //let newList = { name: listName, icon: selectedIcon };
            //lists.push(newList);

            // Update drawer UI
            //updateDrawer();
            //console.log("the lists is ");
            //console.log(lists);
            // Close the modal
            //let modal = bootstrap.Modal.getInstance(document.getElementById("exampleModal"));
            //modal.hide();

            // Reset form fields
            //document.getElementById("recipient-name").value = "";
            //document.getElementById("selectedIcon").value = "";
        }

        // Function to update the sidebar (drawer)
        function updateDrawer() {
            let drawerContainer = document.querySelector(".list-group"); // The list container in drawer.html
            drawerContainer.innerHTML = ""; // Clear existing lists

            lists.forEach((list, index) => {
                let listItem = document.createElement("a");
                listItem.href = "#";
                listItem.className = "list-group-item list-group-item-action py-3 lh-sm";

                listItem.innerHTML = `
                    <div class="d-flex w-100 align-items-center justify-content-between">
                        <strong class="mb-1"><i class="${list.icon}"></i> ${list.name}</strong>
                        <small class="text-body-secondary">New</small>
                    </div>
                `;

                drawerContainer.appendChild(listItem);
            });
        }

        // Attach event listener to form submission
        document.querySelector(".modal-body form").addEventListener("submit", addNewList);

        //border-bottom scrollarea
         document.querySelectorAll(".scrollarea form").forEach(link=>{
            link.addEventListener("click",()=>{
                //alert("hello world");
                event.preventDefault();
                debugger;
                if (link.classList.contains("active")){
                $.ajax({
                          url: "detail" + link.getAttribute("action"), // Endpoint to fetch updated data
                          method: "GET",
                          success: function(response) {//edit-list ms-5 d-flex
                                        $(".edit-list.ms-5.d-flex").html(response); // Update the table with new data
                                        additemtocurrentlist(event);
                                        eventlistenerforlistdetail();
                                            },
                                            error: function() {
                                                        alert("Error fetching data.");
                                            }
                      });

                }
                //console.log(link);
            });
         });




        additemtocurrentlist(event);
        ////current list add item
        /*        var input = document.getElementById("userinput");

                console.log(input);
                var ul = document.getElementById("currentul");
                console.log(ul);

                var button = document.getElementById("currententer");
                console.log(button);
                if (button){
                button.addEventListener("click", function(event) {
                  event.preventDefault();
                  alert("hello world");
                  var li = document.createElement("li");

                  li.className = 'list-group-item';

                  li.innerHTML = `
                        <div class="input-group mb-3 d-flex align-items-center justify-content-center currentlistitem">
                            <div class="input-group-text">
                                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
                            </div>
                            <div class="form-control">
                                input.value
                            </div>
                            <button class="input-group-text">
                                <i class="bi bi-trash"></i>
                            </button>
                        </div>
                  `;
                  ul.appendChild(li);
                });
                }*/
        //////////////////////////////////////////////////////////////////////

        /* addEventListener("submit", function(event){
                                event.preventDefault();
                                $.ajax({
                                    url: "/refresh-users", // Endpoint to fetch updated data
                                    method: "GET",
                                    success: function(response) {
                                        $("#userTable").html(response); // Update the table with new data
                                    },
                                    error: function() {
                                        alert("Error fetching data.");
                                    }
                                });
         });*/



        /*<div class="input-group mb-3 d-flex align-items-center justify-content-center currentlistitem">
                    <div class="input-group-text">
                        <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
                    </div>
                    <input type="text" class="form-control" aria-label="Text input with checkbox">
                    <button class="input-group-text">
                        <i class="bi bi-trash"></i>
                    </button>
                </div>*/

        /*lists.forEach((list, index) => {
                        let listItem = document.createElement("a");
                        listItem.href = "#";
                        listItem.className = "list-group-item list-group-item-action py-3 lh-sm";

                        listItem.innerHTML = `
                            <div class="d-flex w-100 align-items-center justify-content-between">
                                <strong class="mb-1"><i class="${list.icon}"></i> ${list.name}</strong>
                                <small class="text-body-secondary">New</small>
                            </div>
                        `;

                        drawerContainer.appendChild(listItem);
                    });*/

});



/*
document.addEventListener("DOMContentLoaded", function () {
    let lists = []; // Array to store lists

    // Function to handle adding new list
    function addNewList(event) {
        event.preventDefault(); // Prevent page reload

        // Get input values
        let listName = document.getElementById("recipient-name").value.trim();
        let selectedIcon = document.getElementById("selectedIcon").value;

        if (listName === "") {
            alert("Please enter a list name.");
            return;
        }

        // Add new list to the array
        let newList = { name: listName, icon: selectedIcon };
        lists.push(newList);

        // Update drawer UI
        updateDrawer();

        // Close the modal
        let modal = bootstrap.Modal.getInstance(document.getElementById("exampleModal"));
        modal.hide();

        // Reset form fields
        document.getElementById("recipient-name").value = "";
        document.getElementById("selectedIcon").value = "";
    }

    // Function to update the sidebar (drawer)
    function updateDrawer() {
        let drawerContainer = document.querySelector(".list-group"); // The list container in drawer.html
        drawerContainer.innerHTML = ""; // Clear existing lists

        lists.forEach((list, index) => {
            let listItem = document.createElement("a");
            listItem.href = "#";
            listItem.className = "list-group-item list-group-item-action py-3 lh-sm";

            listItem.innerHTML = `
                <div class="d-flex w-100 align-items-center justify-content-between">
                    <strong class="mb-1"><i class="${list.icon}"></i> ${list.name}</strong>
                    <small class="text-body-secondary">New</small>
                </div>
            `;

            drawerContainer.appendChild(listItem);
        });
    }

    // Attach event listener to form submission
    document.querySelector("form").addEventListener("submit", addNewList);

    // Function to handle icon selection
    window.selectIcon = function (button) {
        document.getElementById("selectedIcon").value = button.getAttribute("data-icon");
    };

    // Handle icon search
    document.getElementById("iconSearch").addEventListener("input", function () {
        let searchValue = this.value.toLowerCase();
        document.querySelectorAll(".icon-btn").forEach(button => {
            let icon = button.getAttribute("data-icon").toLowerCase();
            button.style.display = icon.includes(searchValue) ? "inline-block" : "none";
        });
    });
});
*/




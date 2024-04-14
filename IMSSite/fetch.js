


var selectElement = document.getElementById("select");
// Add event listener for change event
selectElement.addEventListener("change", function () {
  var input = document.getElementById("search").value;
  var selectedOption = $(this).val();

  $.ajax({
    url: "Owner.php",
    type: "POST",
    data: {
      selectedOption: selectedOption,
      text: input
    }, // Send selectedOption to PHP
    success: function (response) {
      $("#tableContainer").html(response);
    }
  });
});



window.addEventListener('resize', function () {
  if (window.innerWidth <= 700) {
    document.body.style.overflow = 'hidden'; // Lock scroll
  } else {
    document.body.style.overflow = ''; // Unlock scroll
  }
});

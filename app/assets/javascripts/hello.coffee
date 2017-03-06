getData = () ->
  $.get "/tsdata", (dataset) ->
    $("#dataset").empty()
    $.each dataset, (index, data) ->
      $("#dataset").append $("<li>").text JSON.stringify(data)

$ ->
  getData()
  $("#dataForm").submit (event) ->
    event.preventDefault()
    $.ajax
      url: event.target.action
      type: event.target.method
      contentType: "application/json"
      dataType: "json"
      data: JSON.stringify({
        station: $("#dataStation").val()
        status: ($("#dataStatus").val() == 'true')
        temp: parseFloat $("#dataTemp").val()
        humid: parseFloat $("#dataHumid").val()
        })
      success: () ->
        getData()
        $("#dataStation").val("")
        $("#dataStatus").val("")
        $("#dataTemp").val("")
        $("#dataHumid").val("")
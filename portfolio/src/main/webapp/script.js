// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

/** Fetches langauge data and uses it to create a chart. */
function drawChart() {
  fetch('/language-data').then(response => response.json())
  .then((languageVotes) => {
    const data = new google.visualization.DataTable();
    data.addColumn('string', 'Language');
    data.addColumn('number', 'Votes');
    Object.keys(languageVotes).forEach((language) => {
      data.addRow([language, languageVotes[language]]);
    });

    const options = {
      'title': 'Favorite Programming Languages',
      'width':600,
      'height':500
    };

    const chart = new google.visualization.ColumnChart(
        document.getElementById('chart-container'));
    chart.draw(data, options);
  });
}


/** Fetches comments from the server and adds them to the DOM. */
function loadComments() {    
  fetch('/data').then(response => response.json()).then((comments) => {
    const commentListElement = document.getElementById('text-input');
    comments.forEach((comment) => {
      commentListElement.appendChild(createCommentElement(comment));
    })
  });
}

/** Creates an element that represents a comment */
function createCommentElement(comment) {
  const commentElement = document.createElement('li');
  commentElement.className = 'comment';

  const nameElement = document.createElement('span');
  nameElement.innerText = comment;
  commentElement.appendChild(nameElement);
  return commentElement;
}
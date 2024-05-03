$(document).ready(function () {
    // 日付ごとの総カロリーを取得
    $.getJSON("/meals/sum-calories-by-date", function (data) {
        // グラフのデータを準備
        var labels = [];
        var calories = [];
        $.each(data, function (date, calorie) {
            labels.push(date);
            calories.push(calorie);
        });

        // 日付を新しい順にソート
        labels.sort().reverse();
        var sortedCalories = [];
        for (var i = 0; i < labels.length; i++) {
            sortedCalories.push(data[labels[i]]);
        }

        // グラフを描画
        var ctx = document.getElementById('calorieChart').getContext('2d');
        var calorieChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: '総カロリー',
                    data: sortedCalories,
                    borderColor: 'rgba(75, 192, 192, 1)',
                    backgroundColor: 'rgba(75, 192, 192, 0.2)'
                }]
            },
            options: {
                responsive: false,
                scales: {
                    x: {
                        title: {
                            display: true,
                            text: '日付'
                        },
                        reverse: true
                    },
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'カロリー(kcal)'
                        }
                    }
                },
                layout: {
                    padding: {
                        right: 50 // 右側のパディングを増やす
                    }
                }
            }
        });
    });
});
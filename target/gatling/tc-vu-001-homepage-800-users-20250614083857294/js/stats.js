var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "1600",
        "ok": "1594",
        "ko": "6"
    },
    "minResponseTime": {
        "total": "12",
        "ok": "12",
        "ko": "40246"
    },
    "maxResponseTime": {
        "total": "60008",
        "ok": "39420",
        "ko": "60008"
    },
    "meanResponseTime": {
        "total": "2467",
        "ok": "2300",
        "ko": "46827"
    },
    "standardDeviation": {
        "total": "5772",
        "ok": "5084",
        "ko": "6336"
    },
    "percentiles1": {
        "total": "587",
        "ok": "584",
        "ko": "46161"
    },
    "percentiles2": {
        "total": "1596",
        "ok": "1576",
        "ko": "47401"
    },
    "percentiles3": {
        "total": "12845",
        "ok": "12006",
        "ko": "60008"
    },
    "percentiles4": {
        "total": "31852",
        "ok": "28409",
        "ko": "60008"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 984,
    "percentage": 61.5
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 134,
    "percentage": 8.375
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 476,
    "percentage": 29.75
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 6,
    "percentage": 0.375
},
    "meanNumberOfRequestsPerSecond": {
        "total": "6.48",
        "ok": "6.45",
        "ko": "0.02"
    }
},
contents: {
"req_access-homepage-2121328426": {
        type: "REQUEST",
        name: "Access Homepage",
path: "Access Homepage",
pathFormatted: "req_access-homepage-2121328426",
stats: {
    "name": "Access Homepage",
    "numberOfRequests": {
        "total": "800",
        "ok": "800",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "12",
        "ok": "12",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "11494",
        "ok": "11494",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "906",
        "ok": "906",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1811",
        "ok": "1811",
        "ko": "-"
    },
    "percentiles1": {
        "total": "166",
        "ok": "166",
        "ko": "-"
    },
    "percentiles2": {
        "total": "675",
        "ok": "675",
        "ko": "-"
    },
    "percentiles3": {
        "total": "5047",
        "ok": "5047",
        "ko": "-"
    },
    "percentiles4": {
        "total": "9227",
        "ok": "9092",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 621,
    "percentage": 77.625
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 32,
    "percentage": 4.0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 147,
    "percentage": 18.375
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "3.24",
        "ok": "3.24",
        "ko": "-"
    }
}
    },"req_access-homepage-347374531": {
        type: "REQUEST",
        name: "Access Homepage Redirect 1",
path: "Access Homepage Redirect 1",
pathFormatted: "req_access-homepage-347374531",
stats: {
    "name": "Access Homepage Redirect 1",
    "numberOfRequests": {
        "total": "800",
        "ok": "794",
        "ko": "6"
    },
    "minResponseTime": {
        "total": "342",
        "ok": "342",
        "ko": "40246"
    },
    "maxResponseTime": {
        "total": "60008",
        "ok": "39420",
        "ko": "60008"
    },
    "meanResponseTime": {
        "total": "4027",
        "ok": "3703",
        "ko": "46827"
    },
    "standardDeviation": {
        "total": "7647",
        "ok": "6683",
        "ko": "6336"
    },
    "percentiles1": {
        "total": "908",
        "ok": "893",
        "ko": "46161"
    },
    "percentiles2": {
        "total": "3024",
        "ok": "2894",
        "ko": "47401"
    },
    "percentiles3": {
        "total": "22563",
        "ok": "20041",
        "ko": "60008"
    },
    "percentiles4": {
        "total": "37694",
        "ok": "32248",
        "ko": "60008"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 363,
    "percentage": 45.375
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 102,
    "percentage": 12.75
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 329,
    "percentage": 41.125
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 6,
    "percentage": 0.75
},
    "meanNumberOfRequestsPerSecond": {
        "total": "3.24",
        "ok": "3.21",
        "ko": "0.02"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}

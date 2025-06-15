var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "800",
        "ok": "800",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "17",
        "ok": "17",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "19263",
        "ok": "19263",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "313",
        "ok": "313",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1189",
        "ok": "1189",
        "ko": "-"
    },
    "percentiles1": {
        "total": "250",
        "ok": "290",
        "ko": "-"
    },
    "percentiles2": {
        "total": "408",
        "ok": "408",
        "ko": "-"
    },
    "percentiles3": {
        "total": "609",
        "ok": "609",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1452",
        "ok": "1452",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 784,
    "percentage": 98.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 4,
    "percentage": 0.5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 12,
    "percentage": 1.5
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "4.35",
        "ok": "4.35",
        "ko": "-"
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
        "total": "400",
        "ok": "400",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "17",
        "ok": "17",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "19263",
        "ok": "19263",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "174",
        "ok": "174",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1659",
        "ok": "1659",
        "ko": "-"
    },
    "percentiles1": {
        "total": "23",
        "ok": "23",
        "ko": "-"
    },
    "percentiles2": {
        "total": "27",
        "ok": "27",
        "ko": "-"
    },
    "percentiles3": {
        "total": "68",
        "ok": "68",
        "ko": "-"
    },
    "percentiles4": {
        "total": "8765",
        "ok": "8765",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 397,
    "percentage": 99.25
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 0,
    "percentage": 0.0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 3,
    "percentage": 0.75
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.17",
        "ok": "2.17",
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
        "total": "400",
        "ok": "400",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "287",
        "ok": "287",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "1746",
        "ok": "1746",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "451",
        "ok": "451",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "195",
        "ok": "195",
        "ko": "-"
    },
    "percentiles1": {
        "total": "405",
        "ok": "405",
        "ko": "-"
    },
    "percentiles2": {
        "total": "486",
        "ok": "486",
        "ko": "-"
    },
    "percentiles3": {
        "total": "677",
        "ok": "677",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1532",
        "ok": "1532",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 387,
    "percentage": 96.75
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 4,
    "percentage": 1.0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 9,
    "percentage": 2.25
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "2.17",
        "ok": "2.17",
        "ko": "-"
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

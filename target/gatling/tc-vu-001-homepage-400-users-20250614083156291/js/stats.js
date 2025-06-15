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
        "total": "12",
        "ok": "12",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "10353",
        "ok": "10353",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "490",
        "ok": "490",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "830",
        "ok": "830",
        "ko": "-"
    },
    "percentiles1": {
        "total": "393",
        "ok": "393",
        "ko": "-"
    },
    "percentiles2": {
        "total": "528",
        "ok": "528",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1282",
        "ok": "1282",
        "ko": "-"
    },
    "percentiles4": {
        "total": "4882",
        "ok": "4882",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 706,
    "percentage": 88.25
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 49,
    "percentage": 6.125
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 45,
    "percentage": 5.625
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
        "total": "12",
        "ok": "12",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "3314",
        "ok": "3314",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "175",
        "ok": "175",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "366",
        "ok": "366",
        "ko": "-"
    },
    "percentiles1": {
        "total": "88",
        "ok": "88",
        "ko": "-"
    },
    "percentiles2": {
        "total": "159",
        "ok": "159",
        "ko": "-"
    },
    "percentiles3": {
        "total": "464",
        "ok": "464",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2753",
        "ok": "2753",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 390,
    "percentage": 97.5
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 2,
    "percentage": 0.5
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 8,
    "percentage": 2.0
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
        "total": "383",
        "ok": "383",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "10353",
        "ok": "10353",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "805",
        "ok": "805",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1023",
        "ok": "1023",
        "ko": "-"
    },
    "percentiles1": {
        "total": "516",
        "ok": "516",
        "ko": "-"
    },
    "percentiles2": {
        "total": "715",
        "ok": "715",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1965",
        "ok": "1965",
        "ko": "-"
    },
    "percentiles4": {
        "total": "5635",
        "ok": "5635",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 316,
    "percentage": 79.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 47,
    "percentage": 11.75
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 37,
    "percentage": 9.25
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

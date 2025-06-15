var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "2000",
        "ok": "2000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "9",
        "ok": "9",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2881",
        "ok": "2881",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "344",
        "ok": "344",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "332",
        "ok": "332",
        "ko": "-"
    },
    "percentiles1": {
        "total": "392",
        "ok": "392",
        "ko": "-"
    },
    "percentiles2": {
        "total": "506",
        "ok": "506",
        "ko": "-"
    },
    "percentiles3": {
        "total": "911",
        "ok": "911",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1577",
        "ok": "1577",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 1858,
    "percentage": 92.9
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 94,
    "percentage": 4.7
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 48,
    "percentage": 2.4
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "6.54",
        "ok": "6.54",
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
        "total": "1000",
        "ok": "1000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "9",
        "ok": "9",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "885",
        "ok": "885",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "99",
        "ok": "99",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "116",
        "ok": "116",
        "ko": "-"
    },
    "percentiles1": {
        "total": "70",
        "ok": "70",
        "ko": "-"
    },
    "percentiles2": {
        "total": "100",
        "ok": "100",
        "ko": "-"
    },
    "percentiles3": {
        "total": "352",
        "ok": "352",
        "ko": "-"
    },
    "percentiles4": {
        "total": "618",
        "ok": "613",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 998,
    "percentage": 99.8
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 2,
    "percentage": 0.2
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 0,
    "percentage": 0.0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "3.27",
        "ok": "3.27",
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
        "total": "1000",
        "ok": "1000",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "381",
        "ok": "381",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2881",
        "ok": "2881",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "588",
        "ok": "588",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "295",
        "ok": "295",
        "ko": "-"
    },
    "percentiles1": {
        "total": "504",
        "ok": "504",
        "ko": "-"
    },
    "percentiles2": {
        "total": "612",
        "ok": "612",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1168",
        "ok": "1168",
        "ko": "-"
    },
    "percentiles4": {
        "total": "1936",
        "ok": "1932",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 860,
    "percentage": 86.0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 92,
    "percentage": 9.2
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 48,
    "percentage": 4.8
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 0,
    "percentage": 0.0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "3.27",
        "ok": "3.27",
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
